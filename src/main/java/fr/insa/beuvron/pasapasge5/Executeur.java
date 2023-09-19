/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

import java.util.ArrayList;
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
        for (int i = 0; i < this.aFaire.size(); i++) {
            SaitFaire unO = this.aFaire.get(i);
            unO.faitLe();
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Maison m1 = new Maison("m1", true);
        Maison m2 = new Maison("m2", false);
        Info info = new Info("coucou");
        AfficheInfo1 aff1 = new AfficheInfo1(info);
        Info.AfficheInfo2 aff2 = new Info.AfficheInfo2(info);
        Info.AfficheInfo3 aff3 = info.new AfficheInfo3();
        List<SaitFaire> aFaire = new ArrayList<>(List.of(p1, m1, m2, aff1,
                aff2, aff3));
        aFaire.add(new SaitFaire() {
            @Override
            public void faitLe() {
                System.out.println(this.getClass().getName()
                        + " : " + info.getTexte());
            }
        });
        aFaire.add(() -> {
            System.out.println("lambda"
                    + " : " + info.getTexte());
        });

        Executeur exec = new Executeur(aFaire);
        exec.faitTout();
    }

}
