/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author Angel
 */
public class Servidor {
    public static ArrayList<Socket> arrayClientes = new ArrayList(); //array para almacenzar todas las conexiones al servidor
    public static void main(String args[]){
        
        try {
            ServerSocket server = new ServerSocket(9090);
            System.out.println("Servidor a la escucha");
            while(true){
                Socket socket = server.accept(); //acepta conexión
                arrayClientes.add(socket); //añade usuario al array
                HiloServidor hilo = new HiloServidor(socket);
                hilo.start(); //lanza hilo
            }
            
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
}
