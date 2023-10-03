/*
    Copyright 2000-2014 Francois de Bertrand de Beuvron

    This file is part of UtilsBeuvron.

    UtilsBeuvron is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UtilsBeuvron is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UtilsBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.utils.matrice;

import fr.insa.beuvron.utils.StringUtil;
import java.util.List;

/**
 *
 * @author francois
 */
public class MatriceToText {

    public static String[][] fromObjectToString(String[][] mat) {
        String[][] res = new String[mat.length][];
        for (int i = 0; i < mat.length; i++) {
            if (mat[i] != null) {
                res[i] = new String[mat[i].length];
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = mat[i][j].toString();
                }
            }
        }
        return res;
    }

    public static String[][] fromListOfListToArrays(List<List<String>> mat) {
        String[][] res = new String[mat.size()][];
        for (int i = 0; i < mat.size(); i++) {
            if (mat.get(i) != null) {
                res[i] = new String[mat.get(i).size()];
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = mat.get(i).get(j);
                }
            }
        }
        return res;
    }

    /**
     * nombre de colonnes de la matrice.
     *
     * @return max du nombre d'éléments dans chaque ligne.
     */
    public static int nbrCol(Object[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i] != null && mat[i].length > res) {
                res = mat[i].length;
            }
        }
        return res;
    }

    public static int[] largeurCols(String[][] mat) {
        int[] res = new int[nbrCol(mat)];
        for (int j = 0; j < res.length; j++) {
            for (int i = 0; i < mat.length; i++) {
                if (mat[i] != null && mat[i].length > j) {
                    if (mat[i][j] != null && mat[i][j].length() > res[j]) {
                        res[j] = mat[i][j].length();
                    } else if (mat[i][j] == null) {
                        res[j] = Math.max(res[j], "null".length());
                    }
                }
            }
        }
        return res;
    }

    private static void collectHline(int[] largeurs, StringBuilder collect) {
        collect.append("+");
        for (int i = 0; i < largeurs.length; i++) {
            collect.append(StringUtil.mult("-", largeurs[i]+2));
            collect.append("+");
        }
        collect.append("\n");
    }

    public static String formatMat(List<List<String>> mat, boolean headers) {
        return formatMat(fromListOfListToArrays(mat), headers);
    }

    public static String formatMat(String[][] mat, boolean headers) {
        StringBuilder res = new StringBuilder();
        int[] largeurs = largeurCols(mat);
        collectHline(largeurs, res);
        for (int i = 0; i < mat.length; i++) {
            if (mat[i] == null) {
                res.append("null\n");
            } else {
                res.append("| ");
                for (int j = 0; j < mat[i].length; j++) {
                    String elem;
                    if (mat[i][j] == null) {
                        elem = "null";
                    } else {
                        elem = mat[i][j];
                    }
                    res.append(StringUtil.padRight(elem, largeurs[j]));
                    res.append(" | ");
                }
                res.append("\n");
            }
            if (headers && i == 0) {
                collectHline(largeurs, res);
            }
        }
        collectHline(largeurs, res);
        return res.toString();
    }

}
