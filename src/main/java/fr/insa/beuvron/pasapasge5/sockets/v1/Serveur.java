/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapasge5.sockets.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

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
        System.out.println("attend le client");
        Socket soc = server.accept();
        System.out.println("client ok, attend ligne");
        try (BufferedReader in
                = new BufferedReader(
                        new InputStreamReader(
                                soc.getInputStream(), Charset.forName("UTF8")))) {
            String nextLine;
            while((nextLine = in.readLine()) != null) {
                System.out.println("recu : " + nextLine);
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
