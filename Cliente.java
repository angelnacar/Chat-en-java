/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class Cliente extends Thread {
    private Socket socket;
    public Cliente(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        try {
            String texto = ""; //mensaje que envia
            String nick = ""; //nombre de usuario en el chat
            Socket conexion = new Socket("localhost", 9090);
            Cliente cliente = new Cliente(conexion);
            cliente.start(); //crea hilo para que esté a la escucha y recibir mensajes mientras envía a la vez
            System.out.println("Escribe nick");
            BufferedReader teclado2 = new BufferedReader(new InputStreamReader(System.in));
            nick = teclado2.readLine();

            while (!texto.equals("adios")) {

                System.out.println("Escribe texto");
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream envio = new DataOutputStream(conexion.getOutputStream());
                DataOutputStream nombre = new DataOutputStream(conexion.getOutputStream());
               
                texto = teclado.readLine();
                envio.writeUTF(texto);
                nombre.writeUTF(nick);
               

            }
            conexion.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
              DataInputStream nombre = new DataInputStream(socket.getInputStream()); //nick del usuario que envia el mensaje
              DataInputStream fentrada = new DataInputStream(socket.getInputStream()); //mensaje
              System.out.println(""+nombre.readUTF()+" dice: "+fentrada.readUTF());

            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
           
            
        }
    }

}
