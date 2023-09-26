/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.rappels;

import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public class Cercle extends FigureSimple {

    private Point centre;
    private double rayon;

    public Cercle(Color couleur, Point centre, double rayon) {
        super(couleur);
        this.centre = centre;
        if (rayon < 0) {
            throw new Error("n'importe quoi doit être positif");
        }
        this.rayon = rayon;
    }

   public Cercle(Point centre, double rayon) {
        this(Color.BLUE,centre,rayon);
    }

    @Override
      public double maxX() {
          return this.centre.maxX() + this.rayon;
      } 

    @Override
      public double minX() {
          return this.centre.minX() - this.rayon;
      } 

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
