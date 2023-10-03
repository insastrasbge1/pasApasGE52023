/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.cours.multiTache.exemplesCours.trieurs;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class TriMergeParallele implements Runnable {
    
    public int[] tab;
    public int min;
    public int max;

    public TriMergeParallele(int[] tab, int min, int max) {
        this.tab = tab;
        this.min = min;
        this.max = max;
    }
    
    
    @Override
    public void run() {
        if (max - min < 3) {
            if (tab[min] > tab[max - 1]) {
                int temp = tab[min];
                tab[min] = tab[max - 1];
                tab[max - 1] = temp;
            }
        } else {
            int milieu = (max + min) / 2;
            TriMergeParallele gauche = new TriMergeParallele(this.tab, min, milieu);
            Thread tgauche = new Thread(gauche);
            tgauche.start();
            
            TriMergeParallele droite = new TriMergeParallele(this.tab, milieu, max);
            Thread tdroite = new Thread(droite);
            tdroite.start();
            
            try {
                tgauche.join();
                tdroite.join();
            } catch (InterruptedException ex) {
                throw new Error(ex);
            }
            
            TriMergeSequentiel.fusion(tab, min,milieu, max);
        }
    }
    
    public static void testfusionparallele(){
        int[] t1 = Testeur.tabAlea(1000, 1000000);
        TriMergeParallele trieur1 = new TriMergeParallele(t1, 0, t1.length);
        Thread ttrieur1 = new Thread(trieur1);
        ttrieur1.start();
        
        try {
            ttrieur1.join();
        } catch (InterruptedException ex) {
            throw new Error(ex);
        }
        
        if (Testeur.verifieTrie(t1)){
            System.out.println("Tri réussi !");
        }else{
            System.out.println("echec");
        }
        
    }
    
    public static void main(String[] args) {
        testfusionparallele();
    }
    
}
