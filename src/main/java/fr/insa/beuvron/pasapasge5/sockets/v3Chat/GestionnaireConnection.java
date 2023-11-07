/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.sockets.v3Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author francois
 */
public class GestionnaireConnection implements Runnable {
    
    private Socket soc;
    private int nbr;
    
    private LinkedList<String> toSend;
    

    public GestionnaireConnection(Socket soc, int nbr) {
        this.soc = soc;
        this.nbr = nbr;
        this.toSend = new LinkedList<>();
    }
    
    public void send(String message) {
        synchronized (this.toSend) {
            this.toSend.offer(message);
            this.toSend.notify();
        }
    }

    public void run() {
        System.out.println("Gestion du client " + nbr + " commence");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream(), Charset.forName("UTF8")))) {
            String nextLine;
            while ((nextLine = in.readLine()) != null) {
                System.out.println("recu de " + soc.getRemoteSocketAddress() + " : " + nextLine);
            }
            System.out.println("Gestion du client " + nbr + " fini");
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }
    
}
