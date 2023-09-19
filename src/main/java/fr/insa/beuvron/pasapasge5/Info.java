/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class Info {

    private String texte;
    
    public class AfficheInfo3 implements SaitFaire{

        @Override
        public void faitLe() {
            System.out.println(this.getClass().getName() + " : "
                    + texte);
        }
        
    }

    public Info(String texte) {
        this.texte = texte;
    }

    /**
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    public static class AfficheInfo2 implements SaitFaire {

        private Info message;

        public AfficheInfo2(Info message) {
            this.message = message;
        }

        /**
         * @return the message
         */
        public Info getMessage() {
            return message;
        }

        @Override
        public void faitLe() {
            System.out.println(this.getClass().getName() + " : "
                    + this.message.getTexte());
        }

    }

}
