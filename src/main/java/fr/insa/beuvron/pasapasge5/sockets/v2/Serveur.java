/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.sockets.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fdebertranddeb01
 */
public class Serveur {

    public static final String HOST = "localhost";
//    public static final String HOST = "10.171.10.141";
    public static final int PORT = 10001;

    public static void attendConnection() throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        int nbrConnect = 0;
        while (true) {
            System.out.println("attend le client (nbr connectés : " + nbrConnect + ")");
            Socket soc = server.accept();
            nbrConnect++;
            System.out.println("client " + nbrConnect + " connecté");
            Thread gere = new Thread(new GereConnection(soc, nbrConnect));
            gere.start();
        }
    }

    public static class GereConnection implements Runnable {

        private Socket soc;
        private int nbr;

        public GereConnection(Socket soc, int nbr) {
            this.soc = soc;
            this.nbr = nbr;
        }

        public void run() {
            System.out.println("Gestion du client " + nbr + " commence");
            try (BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(
                                    soc.getInputStream(), Charset.forName("UTF8")))) {
                String nextLine;
                while ((nextLine = in.readLine()) != null) {
                    System.out.println("recu de "
                            + soc.getRemoteSocketAddress()
                            + " : " + nextLine);
                }
                System.out.println("Gestion du client " + nbr + " fini");
            } catch (IOException ex) {
                throw new Error(ex);
            }
        }
    }

    public static void main(String[] args) {
        try {
            attendConnection();
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }

}
