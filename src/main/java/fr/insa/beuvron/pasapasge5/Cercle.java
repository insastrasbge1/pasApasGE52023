/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class Cercle {
    
    private Point centre;
    private double rayon;
    
    public double getRayon() {
        return this.rayon;
    }
    
    public void setRayon(double rayon) {
        if (rayon < 0) {
            throw new Error("n'importe quoi doit être positif");
        }
        this.rayon = rayon;
    }
    
    
      
    
}
