/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

//import chat.Servidor.arrayClientes;
import static chat.Servidor.arrayClientes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author Angel
 */
public class HiloServidor extends Thread {

    Socket socket;
    DataInputStream flujo_entrada, flujo_entrada2;
    DataOutputStream flujo_salida,flujo_salida2;
    

    public HiloServidor(Socket socket) throws IOException {
        this.socket = socket;
        flujo_entrada = new DataInputStream(socket.getInputStream()); //flujo de entrada para el texto
        flujo_entrada2 = new DataInputStream(socket.getInputStream()); //flujo de entrada para el nombre que lo envia

    }

    @Override
    public void run() {
        String texto = "";
        String nombre = "";
        

        try {

            while (!texto.equals("adios")) {

                texto = flujo_entrada.readUTF();
                nombre = flujo_entrada2.readUTF();
                System.out.println("El cliente " + nombre + " dice " + texto);
                
                 for(int i = 0;i<Servidor.arrayClientes.size();i++){ //controla que el mensaje enviado por un usuario no se le reenvie así mismo
                    if(!socket.toString().equals(arrayClientes.get(i).toString())){
                        flujo_salida = new DataOutputStream(arrayClientes.get(i).getOutputStream());
                        flujo_salida2 = new DataOutputStream(arrayClientes.get(i).getOutputStream());
                        flujo_salida2.writeUTF(nombre);
                        flujo_salida.writeUTF(texto);
                    
                     }
                 }   
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
