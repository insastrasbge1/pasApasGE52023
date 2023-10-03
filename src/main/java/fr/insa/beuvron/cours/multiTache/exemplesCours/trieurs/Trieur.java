/*
Copyright 2000- Francois de Bertrand de Beuvron

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
package fr.insa.beuvron.cours.multiTache.exemplesCours.trieurs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author francois
 */
public abstract class Trieur {

    // on ne respecte pas l'encapsulation : le but est que ces attributs
    // soient facilement accessible dans les sous-classes
    // les noms du tableau et des index min/max sont volontairement
    // courts pour ne pas surcharger l'écriture des algorithme
    /**
     * le trie devrait s'arréter avec un statut TIMEOUT si l'instant courant est
     * supérieur à ce temps limite.
     */
    protected long timeLimit;

    /**
     * le trie devrait s'arréter avec un statut TOO_MUCH_THREADS si ce compteur
     * est a zéro et que l'algorithme doit créer un nouveau thread
     */
    protected AtomicInteger threadCounter;

    /**
     * le tableau a trier
     */
    protected int[] tab;

    /**
     * le tableau est trié pour les index i tels que {@code min <= i < max}
     */
    protected int min;

    /**
     * le tableau est trié pour les index i tels que {@code min <= i < max}
     */
    protected int max;

    /**
     * un constructeur avec mêmes paramètres doit être présent dans toutes les
     * sous-classes pour pouvoir être utilisée par le Testeur.
     */
    public Trieur(long timeLimit, AtomicInteger threadCounter, int[] tab, int minIndex, int maxIndex) {
        this.timeLimit = timeLimit;
        this.threadCounter = threadCounter;
        this.tab = tab;
        this.min = minIndex;
        this.max = maxIndex;
    }

    /**
     * un trieur partiel partage le temps limite, le compteur de thread et le
     * tableau avec son créateur.
     */
    protected Trieur(Trieur createur, int minIndex, int maxIndex) {
        this(createur.timeLimit, createur.threadCounter, createur.tab, minIndex, maxIndex);
    }

    public abstract TrieStatus effectueTrie();

    protected static class TimeoutException extends Exception {
    }

    protected static class TooMuchThreadException extends Exception {
    }

    public void ensureNoTimeout() throws TimeoutException {
        if (System.currentTimeMillis() > this.timeLimit) {
            throw new TimeoutException();
        }
    }

    public void declareNewThread() throws TooMuchThreadException {
        if (this.threadCounter.decrementAndGet() < 0) {
            throw new TooMuchThreadException();
        }
    }

}
