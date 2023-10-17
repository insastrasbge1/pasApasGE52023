/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.prodConso;

import fr.insa.beuvron.cours.multiTache.utils.Debug;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class ProdConsoV1 {

    public static class Ressources {

        private LinkedList<String> partage;
        private int capacite;

        public Ressources(int capacite) {
            this.capacite = capacite;
            this.partage = new LinkedList<>();
        }

        public void charge(String ress) {
            synchronized (this.partage) {
                while (this.partage.size() >= this.capacite) {
                    try {
                        this.partage.wait();
                    } catch (InterruptedException ex) {
                        throw new Error("ne dois pas arriver");
                    }
                }
                partage.offer(ress);
                this.partage.notifyAll();
            }
        }

        public String decharge() {
            synchronized (this.partage) {
                while (this.partage.isEmpty()) {
                    try {
                        this.partage.wait();
                    } catch (InterruptedException ex) {
                        throw new Error("interrupt pas normal");
                    }
                }
                String res = this.partage.poll();
                this.partage.notifyAll();
                return res;
            }
        }
    }
    
    public static class Consommateur implements Runnable {

        private Ressources ressource;
        private long tempsConso;
        private String nom;

        public Consommateur(String nom,Ressources ressource, long tempsConso) {
            this.nom = nom;
            this.ressource = ressource;
            this.tempsConso = tempsConso;
        }
        
        
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(this.tempsConso);
                } catch (InterruptedException ex) {
                    throw new Error("interrupt pas normal");
                }
                String recup = this.ressource.decharge();
                System.out.println(this.nom + " <-- " + recup);
            }
        }
        
    }
    
    

}
