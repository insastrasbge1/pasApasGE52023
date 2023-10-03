package fr.insa.beuvron.cours.multiTache.utils;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author francois
 */
public class Lire {

    /**
     *
     * @return
     */
    public static String S() // Lire un String
    {
//        Console cons = System.console();
//        return cons.readLine();
        InputStreamReader inChar = new InputStreamReader(System.in);
        String tmp = "";
        int next;
        try {
            while ((next = inChar.read()) != -1 && ((char) next) != '\n') {
                tmp = tmp + ((char) next) ;
            }
        } catch (IOException e) {
            System.out.println("Erreur de frappe");
            System.exit(0);
        }
        return tmp;
    } // fin de S()

    /**
     *
     * @return
     */
    public static byte b() // Lire un entier byte
    {
        byte x = 0;
        try {
            x = Byte.parseByte(S());
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static short s() // Lire un entier short
    {
        short x = 0;
        try {
            x = Short.parseShort(S());
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static int i() // Lire un entier
    {
        int x = 0;
        try {
            x = Integer.parseInt(S());
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static long l() // Lire un entier long
    {
        long x = 0;
        try {
            x = Integer.parseInt(S());
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static double d() // Lire un double
    {
        double x = 0.0;
        try {
            x = Double.valueOf(S()).doubleValue();
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static float f() // Lire un float
    {
        float x = 0.0f;
        try {
            x = Double.valueOf(S()).floatValue();
        } catch (NumberFormatException e) {
            System.out.println("Format numérique incorrect");
            System.exit(0);
        }
        return x;
    }

    /**
     *
     * @return
     */
    public static char c() // Lire un caractere
    {
        String tmp = S();
        if (tmp.length() == 0) {
            return '\n';
        } else {
            return tmp.charAt(0);
        }
    }
}
