/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.pairImpair;

import fr.insa.beuvron.pasapasge5.ThreadUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class PairImpairSleep {

    public static class Pair implements Runnable {

        private Thread impair;

        @Override
        public void run() {
            long debut = System.currentTimeMillis();
            for (int i = 0; i < 10; i = i + 2) {
                ThreadUtils.sleepSansInterrupt(300);
                System.out.println("Pair : "
                        + (System.currentTimeMillis()-debut)
                        + " --> " + i);
                this.impair.interrupt();
                System.out.println("pair attend");
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException ex) {
                    System.out.println("pair a ete reveille");
                }
            }
        }

        /**
         * @return the impair
         */
        public Thread getImpair() {
            return impair;
        }

        /**
         * @param impair the impair to set
         */
        public void setImpair(Thread impair) {
            this.impair = impair;
        }
    }

    public static class Impair implements Runnable {

        private Thread pair;

        @Override
        public void run() {
            long debut = System.currentTimeMillis();
            for (int i = 1; i < 10; i = i + 2) {
                ThreadUtils.sleepSansInterrupt(200);
                System.out.println("impair attend");
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException ex) {
                    System.out.println("impair a été réveillé");
                }
                System.out.println("Impair : "
                        + (System.currentTimeMillis()-debut)
                        + " --> " + i);
                this.pair.interrupt();
            }
        }

        /**
         * @return the pair
         */
        public Thread getPair() {
            return pair;
        }

        /**
         * @param pair the pair to set
         */
        public void setPair(Thread pair) {
            this.pair = pair;
        }
    }

    public static void gogogo() {
        try {
            Pair p1 = new Pair();
            Impair i1 = new Impair();
            Thread tp1 = new Thread(p1);
            Thread ti1 = new Thread(i1);
            p1.setImpair(ti1);
            i1.setPair(tp1);
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
