/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.pairImpair;

/**
 *
 * @author francois
 */
public class PairImpairV1 {

    public static class Pair implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i = i + 2) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    throw new Error(ex);
                }
                System.out.println("Pair : " + i);
            }
        }
    }

    public static class Impair implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < 10; i = i + 2) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new Error(ex);
                }
                System.out.println("Impair : " + i);
            }
        }
    }

    public static void gogogo() {
        try {
            Pair p1 = new Pair();
            Impair i1 = new Impair();
            Thread tp1 = new Thread(p1);
            Thread ti1 = new Thread(i1);
            tp1.start();
            ti1.start();
            tp1.join();
            ti1.join();
 
            System.out.println("fini");
        } catch (InterruptedException ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        gogogo();
    }

}
