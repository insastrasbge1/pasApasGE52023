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
public class TriBulleSequentiel extends Trieur {

    public TriBulleSequentiel(long timeLimit, AtomicInteger threadCounter, int[] tab, int minIndex, int maxIndex) {
        super(timeLimit, threadCounter, tab, minIndex, maxIndex);
    }

    @Override
    public TrieStatus effectueTrie() {
        for (int i = min; i < max - 1; i++) {
            if (System.currentTimeMillis() > this.timeLimit) {
                return TrieStatus.TIMEOUT;
            }
            for (int j = i + 1; j < max; j++) {
                if (tab[i] > tab[j]) {
                    int temp = tab[i];
                    tab[i] = tab[j];
                    tab[j] = temp;
                }
            }
        }
        return TrieStatus.OK;
    }

}
