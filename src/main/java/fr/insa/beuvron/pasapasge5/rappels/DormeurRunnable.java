/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.rappels;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class DormeurRunnable implements Runnable{

    private String nom;
    private long tempsSommeil;
    
    public DormeurRunnable(String nom,long tempsSommeil) {
        this.nom = nom;
        this.tempsSommeil = tempsSommeil;
    }
    
    
    @Override
    public void run() {
        try {
            System.out.println("Debut de "+this.nom);
            Thread.sleep(this.tempsSommeil);
            System.out.println("fin de "+this.nom);
        } catch (InterruptedException ex) {
            throw new Error(ex);
        }  
    }

    /**
     * @return the tempsSommeil
     */
    public long getTempsSommeil() {
        return tempsSommeil;
    }
    
    public static void test1() {
        try {
            DormeurRunnable d1 = new DormeurRunnable("d1",300);
            DormeurRunnable d2 = new DormeurRunnable("d2",200);
            Thread td1 = new Thread(d1);
            Thread td2 = new Thread(d2);
            td1.start();
            td2.start();
            td1.join();
            td2.join();
            System.out.println("c'est fini");
        } catch (InterruptedException ex) {
            throw new Error(ex);
        }
    }
    
    public static void main(String[] args) {
        test1();
    }
    
}
