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
package fr.insa.beuvron.cours.multiTache.exemplesCours.trieurs;

import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author francois
 */
public class TriMergeSequentiel extends Trieur {

    public TriMergeSequentiel(long timeLimit, AtomicInteger threadCounter,int[] tab,int minIndex,int maxIndex) {
        super(timeLimit, threadCounter, tab,minIndex,maxIndex);
    }
    
    /**
     * un trieur partiel partage le temps limite, le compteur de thread et le
     * tableau avec son créateur.
     */
    protected TriMergeSequentiel(Trieur createur, int minIndex, int maxIndex) {
        this(createur.timeLimit, createur.threadCounter,createur.tab,minIndex,maxIndex);
    }

    @Override
    public TrieStatus effectueTrie() {
        if (System.currentTimeMillis() > this.timeLimit) {
            return TrieStatus.TIMEOUT;
        }
        if (max - min < 3) {
            if (tab[min] > tab[max - 1]) {
                int temp = tab[min];
                tab[min] = tab[max - 1];
                tab[max - 1] = temp;
            }
        } else {
            int milieu = (max + min) / 2;
            TriMergeSequentiel gauche = new TriMergeSequentiel(this, min, milieu);
            TrieStatus st = gauche.effectueTrie();
            if (st != TrieStatus.OK) {
                return st;
            }
            TriMergeSequentiel droite = new TriMergeSequentiel(this, milieu, max);
            st = droite.effectueTrie();
            if (st != TrieStatus.OK) {
                return st;
            }
            fusion(tab, min,milieu, max);
        }
        return TrieStatus.OK;
    }

    public static void fusion(int[] tab, int min,int milieu, int max) {
        int[] fu = new int[max - min];
        int cur1 = min;
        int cur2 = milieu;
        for (int i = 0; i < fu.length; i++) {
            if (cur1 >= milieu) {
                // plus rien dans la première partie
                fu[i] = tab[cur2];
                cur2++;
            } else if (cur2 > max - 1) {
                // plus rien dans la seconde partie
                fu[i] = tab[cur1];
                cur1++;
            } else if (tab[cur1] <= tab[cur2]) {
                fu[i] = tab[cur1];
                cur1++;
            } else {
                fu[i] = tab[cur2];
                cur2++;
            }
        }
        for (int i = 0; i < fu.length; i++) {
            tab[min + i] = fu[i];
        }
    }
}
