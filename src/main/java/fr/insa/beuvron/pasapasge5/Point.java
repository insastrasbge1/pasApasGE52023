/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class Point {
    
    private double px;
    private double py;
    
    public Point() {
        this.px = 0;
        this.py = 0;
    }
    
    public Point(double px,double py) {
        this.px = px;
        this.py = py;
    }
    
    public static Point origine() { 
        Point res = new Point();
        return res;
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
