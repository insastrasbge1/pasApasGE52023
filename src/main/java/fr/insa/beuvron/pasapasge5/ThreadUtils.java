/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class ThreadUtils {

    public static void sleepSansInterrupt(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            throw new Error(ex);
        }

    }

}
