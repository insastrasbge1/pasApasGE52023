/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

import java.util.List;

/**
 *
 * @author francois
 */
public class Executeur {
    
    private List<SaitFaire> aFaire;

    public Executeur(List<SaitFaire> aFaire) {
        this.aFaire = aFaire;
    }

    /**
     * @return the aFaire
     */
    public List<SaitFaire> getaFaire() {
        return aFaire;
    }
    
    public void faitTout() {
        for(int i = 0 ; i < this.aFaire.size() ; i ++) {
            SaitFaire unO = this.aFaire.get(i);
            unO.faitLe();
        }
    }
    
    public static void main(String[] args) {
        Point p1 = new Point(2,3);
        Maison m1 = new Maison("m1",true);
        Maison m2 = new Maison("m2",false);
        List<SaitFaire> aFaire = List.of(p1,m1,m2);
        Executeur exec = new Executeur(aFaire);
        exec.faitTout();
    }
    
    
}
