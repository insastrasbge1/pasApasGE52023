/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public class Point extends FigureSimple {

    private double px;
    private double py;

    public Point() {
        this(Color.BLACK, 0, 0);
    }

    public Point(Color couleur) {
        this(couleur, 0, 0);
    }

    public Point(Color couleur, double px, double py) {
        super(couleur);
        this.px = px;
        this.py = py;
    }

    public Point(double px, double py) {
        this(Color.RED, px, py);
    }

    public static Point origine() {
        Point res = new Point(Color.BLACK);
        return res;
    }

    @Override
    public double maxX() {
        return this.px;

    }

    @Override
    public double minX() {
        return this.px;

    }

    @Override
    public String toString() {
        return "Point(" + this.px + "," + this.py + ")";
    }

    /**
     * @return the px
     */
    public double getPx() {
        return this.px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
    }

}
