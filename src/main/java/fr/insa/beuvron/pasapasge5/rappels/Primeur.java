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
public class Primeur {
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("debut de t1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                throw new Error(ex);
            }
            System.out.println("fin de t1");
        });
        t1.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new Error(ex);
            }
        System.out.println("je continu");
    }
    
}
