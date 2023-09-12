/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class PasApasGE5 {

    public static void main(String[] args) {
        Point p1 = new Point();
        p1.setPx(3);
        System.out.println("p1 : " + p1);
        
        Point p2 = new Point(3,2);
        
        Cercle c = new Cercle();
        c.setRayon(3);
        System.out.print("cercle de rayon : " + c.getRayon());
        c.setRayon(-2);
        System.out.print("cercle maintenant rayon : " + c.getRayon());
    }
}
