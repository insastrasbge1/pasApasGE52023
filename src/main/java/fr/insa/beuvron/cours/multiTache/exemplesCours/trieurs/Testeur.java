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

import fr.insa.beuvron.cours.multiTache.utils.Debug;
import fr.insa.beuvron.utils.exceptions.ExceptionsUtils;
import fr.insa.beuvron.utils.matrice.MatriceToText;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author francois
 */
public class Testeur {

    public static int[] tabAlea(int size, int borneMax) {
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * borneMax);
        }
        return res;
    }

    public static boolean verifieTrie(int[] tab) {
        boolean res = true;
        int i = 0;
        while (res && i < tab.length - 1) {
            res = tab[i] <= tab[i + 1];
            i++;
        }
        return res;
    }

    public static FinalResult test(Class<? extends Trieur> trieurType, int size, int borneMax, long maxTime, int maxThreads) {
        int[] tab = tabAlea(size, borneMax);
        return test(trieurType, tab, maxTime, maxThreads);
    }

    public static FinalResult test(Class<? extends Trieur> trieurType, int[] tab, long maxTime, int maxThreads) {
        long tempsDebut = System.currentTimeMillis();
        try {
            Debug.trace("début de trie : "
                    + trieurType.getSimpleName()
                    + " : size = " + tab.length
            );
            Constructor<? extends Trieur> co = trieurType.getConstructor(long.class, AtomicInteger.class, int[].class, int.class, int.class);
            long tempsFin = tempsDebut + maxTime;
            Trieur trieur = co.newInstance(tempsFin, new AtomicInteger(maxThreads), tab, 0, tab.length);
            TrieStatus trieRes = trieur.effectueTrie();
            long elapsedTime = System.currentTimeMillis() - tempsDebut;
            FinalResult res;
            if (trieRes == TrieStatus.TIMEOUT) {
                res = new FinalResult(FinalStatus.TIMEOUT, elapsedTime);
            } else if (trieRes == TrieStatus.TOO_MUCH_THREADS) {
                res = new FinalResult(FinalStatus.TOO_MUCH_THREADS, elapsedTime);
            } else if (trieRes == TrieStatus.INTERNAL_ERROR) {
                res = new FinalResult(FinalStatus.INTERNAL_ERROR, elapsedTime);
            } else if (verifieTrie(tab)) {
                res = new FinalResult(FinalStatus.OK, elapsedTime);
            } else {
                res = new FinalResult(FinalStatus.SORT_ERROR, elapsedTime);
            }
            Debug.trace("fin trie : " + trieRes);
            return res;
        } catch (Exception ex) {
            Debug.out(Debug.Niveau.ERROR, ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa.beuvron", 5));
            return new FinalResult(FinalStatus.INTERNAL_ERROR, System.currentTimeMillis() - tempsDebut);
        }
    }

    public static List<List<String>> compare(List<Class<? extends Trieur>> trieurs,
            int sizeMin, double multiplieurTaille, int nbrPas, int borneMax,
            long maxTime, int maxThreads) {
        List<List<String>> res = new ArrayList<>(nbrPas + 1);
        Debug.info("Test des trieurs : \n"
                + "  taille initiale : " + sizeMin + "\n"
                + "  taille multipliée par : " + multiplieurTaille + " à chaque pas\n"
                + "  nombre de pas : " + nbrPas + "\n"
                + "  taille max des elements : 0 <= e < " + borneMax + "\n"
                + "  temps maximal d'exécution : " + FinalResult.timeToString(maxTime) + "\n"
                + "  nombre maximal de threads : " + maxThreads + "\n"
                + "  nombre de processeurs : " + Runtime.getRuntime().availableProcessors()
        );
        List<String> curLine = new ArrayList<>(trieurs.size() + 1);
        res.add(curLine);
        curLine.add("taille");
        for (var trieurCL : trieurs) {
            curLine.add(trieurCL.getSimpleName());
        }
        double curMult = 1;
        for (int i = 0; i < nbrPas; i++) {
            int size = sizeMin * (int) curMult;
            Debug.trace("------- taille : " + size);
            curLine = new ArrayList<>(trieurs.size() + 1);
            res.add(curLine);
            curLine.add("" + size);
            int[] leTab = tabAlea(size, borneMax);
            for (var trieurCL : trieurs) {
                Debug.trace("-- trieur : " + trieurCL.getSimpleName());
                int[] copie = Arrays.copyOf(leTab, leTab.length);
                FinalResult resTrie = test(trieurCL, copie, maxTime, maxThreads);
                curLine.add(resTrie.timeOrStatus());
            }
            curMult = curMult * multiplieurTaille;
        }
        return res;
    }

    public static void compareToutEtAffiche(int sizeMin, double multiplieurTaille, int nbrPas, int borneMax,
            long maxTime, int maxThreads) {
        List<Class<? extends Trieur>> trieurs = List.of(
                TriBulleSequentiel.class,
                 TriMergeSequentiel.class
        //                ,TriMergeParallel.class
        );
        List<List<String>> res = compare(trieurs, sizeMin, multiplieurTaille, nbrPas, borneMax, maxTime, maxThreads);
        System.out.println(MatriceToText.formatMat(res, true));
    }

    public static void main(String[] args) {
//        Debug.setNiveauMax(Debug.Niveau.INFO);
        compareToutEtAffiche(1, Math.pow(10, 1.0 / 1), 6, 1000000, 1000, 1000);
    }

}
