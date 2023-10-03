/*
 Copyright 2000-2014 Francois de Bertrand de Beuvron

 This file is part of CoursBeuvron.

 CoursBeuvron is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 CoursBeuvron is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.cours.multiTache.utils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Une horloge pemettant de découpler le temps de simulation et le temps réel.
 *
 * <pre>
 * Dans la simulation, les temps (TS) sont exprimés en ut (unité arbitraire de temps).
 * On veut pouvoir faire la liaison :
 *   temps reel TR (en ms système) {@code  <--> } temps de simulation TS (en ut)
 * De plus, on veut que cette conversion puisse évoluer dynamiquement pour
 * accélérer/ralentir la simulation
 *
 * Utilisation :
 *   . lors de la création, la clock est en pause
 * 
 *   . vous devez appeler {@link #start() } pour commencer le décompte du temps
 *     sur la base de 1 UT tous les {@link #uneUTenMs}
 * 
 *   . vous pouvez appeler {@link #stop() } pour stoper la simulation (il n'y a
 *     plus de décompte d'UT
 * 
 *   . vous pouvez de nouveau appeler {@link #start() } pour relancer une clock
 *     stoppée
 *
 *   . vous pouvez à tout moment demander le temps de simulation actuel (en UT) :
 *     nombre d'UT écoulés depuis la création de la SimulationClock
 *     par la méthode {@link #getSimulationTimeEnUT() }
 *
 *   . vous pouvez demander au Thread courant d'attendre un certain nombre
 *     n d'UT (un équivalent de {@link Thread#sleep(long) } mais en temps de simulation et non en
 *     temps réel) par {@link #metEnAttente(long) }.
 *     Le temps d'attente peut être un peu supérieur à n * ms/ut car on ne compte 
 *     que les ut complets : Si un UT est en cours, le reste
 *     de l'UT courant n'est pas compté dans le nombre d'UT d'attente.
 *     Exemple : 1 UT = 10 ms ; TR = 17 ms ==> TS = 1 ut, et 7ms se sont déjà
 *     écoulées depuis TS = 1 ut. Si l'on demande une attente de 2 ut, on attendra
 *     en fait TS = 4 ut (soit une attente d'au moins 23 ms)
 * {@code
 * 
 * TS    0         1          2        3         4         5         6         7 
 * TR    01234567890123456789012345678901234567890123456789012345678901234567890123456789
 * cur                    ^
 * fin attente (min) :                           ^ 
 * } 
 *
 *   . vous pouvez demander d'interrompre l'attente d'un thread (un équivalent
 *     de {@link Thread#interrupt() } par {@link #interromptAttente(java.lang.Thread) }
 *     Attention : utiliser directement {@link Thread#interrupt() } n'aurait pas
 *     d'effet visible : le thread se remettrait en sleep jusqu'à la fin du délai
 *     en UT indiqué lors de {@link #metEnAttente(long) }
 *
 *   . vous pouvez à tout moment modifier la conversion du temps de simulation
 *     en temps réel grace à {@link #changeUT(long) }.
 *     Les temps de sleep des Thread mis en attente par {@link #metEnAttente(long) }
 *     seront automatiquement recalculés pour que l'attente finisse correctement
 *     à l'UT fixée au départ (qui correspond maintenant à un nouveau temps réel).
 *     Cela permet de changer la vitesse de simulation en cours de simulation.
 *     Note : le changement n'intervient qu'à partir du début de l'ut suivante
 *       Exemple : en supposant que l'on à commencé à TR = 0 ; TS = 0 et ms/ut = 100
 *       le temps courant est 172ms. On change la conversion à ms/ut=10
 *       En fait, on va attendre la fin de l'ut courant (reste 38ms sur 100ms)
 *       avant de prendre en compte la nouvelle conversion. donc attention
 *       si vous avez un temps de simulation très lent, l'accélération peut ne
 *       pas être immédiate quand on change la vitesse ( ms/ut)
 *
 * </pre>
 *
 * @author francois
 */
public class SimulationClock {

    private long uneUTenMs;
    private long tempsSystemDerniereMiseAJour;
    private long tempsSimulationDerniereMiseAJour;
    private long resteMsPourFinUT;
    private boolean enPause;

    private Set<Thread> threadsEnAttente;
    private Set<Thread> threadsInterrompueParUtilisateur;

    /**
     * Cree une SimulationClock. Le temps de simulation commence immédiatement.
     *
     * @param uneUTenMs valeur d'une ut (temps simulation) en ms (temps réel)
     */
    public SimulationClock(long uneUTenMs) {
        this.uneUTenMs = uneUTenMs;
        this.tempsSystemDerniereMiseAJour = System.currentTimeMillis();
        this.resteMsPourFinUT = 0;
        this.tempsSimulationDerniereMiseAJour = 0;
        this.threadsEnAttente = Collections.newSetFromMap(new ConcurrentHashMap<Thread, Boolean>());
        this.threadsInterrompueParUtilisateur = Collections.newSetFromMap(new ConcurrentHashMap<Thread, Boolean>());
        this.enPause = true;
    }

    /**
     * met à jour curUT et Reste en considérant que la conversion Ms/UT est
     * restée constante depuis le dernier checkPoint, et que le simulateur
     * n'était pas en pause
     */
    private void checkPoint() {
        long curTR = System.currentTimeMillis();
        long elapsedTR = curTR - this.tempsSystemDerniereMiseAJour;
        if (elapsedTR < this.resteMsPourFinUT) {
            this.resteMsPourFinUT = this.resteMsPourFinUT - elapsedTR;
        } else {
            elapsedTR = elapsedTR - this.resteMsPourFinUT;
            long elapsedTS = elapsedTR / this.uneUTenMs;
            this.resteMsPourFinUT = elapsedTR % this.uneUTenMs;
            this.tempsSimulationDerniereMiseAJour = this.tempsSimulationDerniereMiseAJour + elapsedTS;
        }
        this.tempsSystemDerniereMiseAJour = curTR;

    }

    /** todoDoc. */
    public synchronized void start() {
        if (this.enPause) {
            this.tempsSystemDerniereMiseAJour = System.currentTimeMillis();
            this.enPause = false;
            this.reveilleLesThreadsEnAttente();
        }
    }

    /** todoDoc. */
    public synchronized void stop() {
        if (!this.enPause) {
            this.checkPoint();
            this.enPause = true;
        }
    }

    /**
     *
     * @return
     */
    public synchronized long getSimulationTimeEnUT() {
        if (this.enPause) {
            return this.tempsSimulationDerniereMiseAJour;
        } else {
            this.checkPoint();
            return this.tempsSimulationDerniereMiseAJour;
        }
    }

    /**
     *
     * @param uneUTenMs
     */
    public synchronized void changeUT(long uneUTenMs) {
        if (this.enPause) {
            this.uneUTenMs = uneUTenMs;
        } else {
            this.checkPoint();
            this.uneUTenMs = uneUTenMs;
            this.reveilleLesThreadsEnAttente();
        }
    }

    /**
     *
     * @param nbrUT
     * @throws InterruptedException
     */
    public void metEnAttente(long nbrUT) throws InterruptedException {
        this.threadsEnAttente.add(Thread.currentThread());
        try {
            long curUT = this.getSimulationTimeEnUT();
            long reveilUT = curUT + nbrUT;
            while (curUT < reveilUT) {
                // +1 ms pour ne pas faire un sleep supplementaire de 1 ms si
                // sleep peu précis
                long estimatedTR;
                if (this.enPause) {
                    estimatedTR = Long.MAX_VALUE;
                } else {
                    synchronized (this) {
                        estimatedTR = (reveilUT - curUT) * this.uneUTenMs + 
                                this.resteMsPourFinUT + 1;
                    }
                }
                try {
                    Thread.sleep(estimatedTR);
                } catch (InterruptedException ex) {
                    // il y a deux cas d'interruption : 
                    // . interruption par la SimulationClock si appel de changeUT
                    //    --> dans ce cas rien à faire : simplement refaire
                    //       un sleep correspondant au nouveau temps reel
                    // . interruption par l'utilisateur (methode interrompAttente)
                    //    --> dans ce cas, on genere une nouvelle InterruptedException
                    Thread t = Thread.currentThread();
                    if (this.threadsInterrompueParUtilisateur.contains(t)) {
                        this.threadsInterrompueParUtilisateur.remove(t);
                        throw new InterruptedException();
                    }
                }
                curUT = this.getSimulationTimeEnUT();
            }
        } finally {
            this.threadsEnAttente.remove(Thread.currentThread());
        }
    }

    /**
     *
     * @param t
     */
    public void interromptAttente(Thread t) {
        this.threadsInterrompueParUtilisateur.add(t);
        t.interrupt();
    }

    private void reveilleLesThreadsEnAttente() {
        for (Thread t : this.threadsEnAttente) {
            t.interrupt();
        }
    }

}
