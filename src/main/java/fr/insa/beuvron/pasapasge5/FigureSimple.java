/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

import javafx.scene.paint.Color;


/**
 *
 * @author francois
 */
public class FigureSimple {
    
    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
    }

    public FigureSimple() {
        this.couleur = Color.GREEN;
    }
    
    

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
    
}