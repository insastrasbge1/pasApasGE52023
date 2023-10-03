/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.partage;

import fr.insa.beuvron.pasapasge5.ThreadUtils;

/**
 *
 * @author francois
 */
public class Main {
       public static void test() {
        long debut = System.currentTimeMillis();
        Partage p1 = new Partage();
        Partage p2 = new Partage();
        
        Incrementeur i1 = new Incrementeur(p1);
        Incrementeur i2 = new Incrementeur(p1);
                Incrementeur i21 = new Incrementeur(p2);
                Incrementeur i22 = new Incrementeur(p2);
                Incrementeur i23 = new Incrementeur(p2);
        Thread ti1 = new Thread(i1);
        Thread ti2 = new Thread(i2);
        Thread ti21 = new Thread(i21);
        Thread ti22 = new Thread(i22);
        Thread ti23 = new Thread(i23);
        ti1.start();
        ti2.start();
        ti21.start();
        ti22.start();
        ti23.start();
        ThreadUtils.joinSansInterrupt(ti1);
        ThreadUtils.joinSansInterrupt(ti2);
        ThreadUtils.joinSansInterrupt(ti21);
        ThreadUtils.joinSansInterrupt(ti22);
        ThreadUtils.joinSansInterrupt(ti23);
        System.out.println("valeur finale p1 : " + p1.compteur);
        System.out.println("valeur finale p2 : " + p2.compteur);
        System.out.println("elapsed time : " + (System.currentTimeMillis() - debut));
        
    }
    
    public static void main(String[] args) {
        test();
    } 
}
