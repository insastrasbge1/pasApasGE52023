/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.sockets.v2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author fdebertranddeb01
 */
public class Client {

    public static void connectePuisEnvoie() throws UnknownHostException, IOException {
        InetAddress adr = InetAddress.getByName(Serveur.HOST);
        try (Socket socOut = new Socket(adr, Serveur.PORT)) {
            System.out.println("socket created : " + socOut.getLocalPort());
            try (BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            socOut.getOutputStream(), Charset.forName("UTF8")))) {
                Scanner sc = new Scanner(System.in);
                String line = "";
                while (!line.equals("FIN")) {
                    System.out.println("ligne à transmettre (FIN pour finir) : ");
                    line = sc.nextLine();
                    out.write(line + "\n");
                    out.flush();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            connectePuisEnvoie();
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }

}
