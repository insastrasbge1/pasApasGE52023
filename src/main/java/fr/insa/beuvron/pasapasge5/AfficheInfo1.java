/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5;

/**
 *
 * @author francois
 */
public class AfficheInfo1 implements SaitFaire{ 
    
    private Info message;

    public AfficheInfo1(Info message) {
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
        System.out.println(this.getClass().getName()+ " : " +
                this.message.getTexte());
    }
    
    
    
}
