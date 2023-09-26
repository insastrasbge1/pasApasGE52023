/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.rappels;

/**
 *
 * @author francois
 */
public class Maison implements SaitFaire{
    
    private String nom;
    private boolean alarm;

    public Maison(String nom,boolean alarm) {
        this.nom = nom;
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "Maison{" + "nom=" + nom + ", alarm=" + alarm + '}';
    }
    
    
    
    public boolean testAlarm() {
        return this.alarm;
    }

    @Override
    public void faitLe() {
        if(this.testAlarm()) {
            System.out.println("ALERTE en " + this);
        }
    }
    
}
