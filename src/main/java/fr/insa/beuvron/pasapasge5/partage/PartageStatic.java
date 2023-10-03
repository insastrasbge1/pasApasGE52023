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
public class PartageStatic {
    
    public static int partage;
    
    public static class Incrementeur implements Runnable {

        @Override
        public void run() {
            for(int i = 0 ; i < 100000000 ; i ++) {
                PartageStatic.partage ++;
            }
        }  
    }
    
    public static void test() {
        long debut = System.currentTimeMillis();
        PartageStatic.partage = 0;
        Incrementeur i1 = new Incrementeur();
        Incrementeur i2 = new Incrementeur();
        Thread ti1 = new Thread(i1);
        Thread ti2 = new Thread(i2);
        ti1.start();
        ti2.start();
        ThreadUtils.joinSansInterrupt(ti1);
        ThreadUtils.joinSansInterrupt(ti2);
        System.out.println("valeur finale : " + PartageStatic.partage);
        System.out.println("elapsed time : " + (System.currentTimeMillis() - debut));
        
    }
    
    public static void main(String[] args) {
        test();
    }
}
