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
    
    public double px;
    public double py;
    
    public static Point origine() { 
        Point res = new Point();
        res.px = 0;
        res.py = 0;
        return res;
    }
    
    
}
