/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.partage;

/**
 *
 * @author francois
 */
public class Incrementeur implements Runnable {

    private Partage partage;

    public Incrementeur(Partage partage) {
        this.partage = partage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            synchronized (this.partage) {
                this.partage.compteur++;
            }
        }
    }

}
