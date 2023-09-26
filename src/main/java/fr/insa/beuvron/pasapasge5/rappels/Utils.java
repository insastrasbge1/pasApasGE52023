/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.rappels;

/**
 *
 * @author francois
 */
public class Utils {
    
    public String unSurDeux(String s) {
        String res = "";
        for(int i = 0 ; i < s.length() ; i = i + 2) {
            res = res + s.charAt(i);
        }
        return res;
    }
    
}
