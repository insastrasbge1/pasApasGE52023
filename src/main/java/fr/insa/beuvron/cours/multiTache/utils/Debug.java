/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.cours.multiTache.utils;

import java.io.PrintWriter;

/**
 * Un petit utilitaire très sommaire pour afficher des informations de débug.
 * @author toto
 */
public class Debug {

    /**
     * on affiche les message tels que niveau {@code <= } MAX_NIVEAU.
     */
    private static int MAX_NIVEAU = 4;
    
    private static PrintWriter OUT = new PrintWriter(System.out);
    
    public static void setNiveauMax(int niveau) {
        MAX_NIVEAU = niveau;
    }
    
    public static void setNiveauMax(Niveau niveau) {
        setNiveauMax(niveau.getNiveau());
    }
    
    
    public void setOut(PrintWriter out) {
        OUT = out;
    }
    
    public void resetOut() {
        OUT = new PrintWriter(System.out);
    }
    
    public static enum Niveau {
        ERROR(1), WARNING(2), INFO(3), TRACE(4);
        
        private int niveau;

        private Niveau(int niveau) {
            this.niveau = niveau;
        }

        public int getNiveau() {
            return niveau;
        }

    }

    public static synchronized void out(int niveau,String message) {
        if (niveau <= MAX_NIVEAU) {
            OUT.println(message);
            OUT.flush();
        }
    }
    
    public static void out(Niveau niveau,String message)  {
        OUT.print(niveau + " : ");
        out(niveau.getNiveau(), message);
    }
    
    public static void erreur(String message) {
        out(Niveau.ERROR,message);
    }
    
    public static void warning(String message) {
        out(Niveau.WARNING,message);
    }
    
    public static void info(String message) {
        out(Niveau.INFO,message);
    }
    
    public static void trace(String message) {
        out(Niveau.TRACE,message);
    }
    

}
