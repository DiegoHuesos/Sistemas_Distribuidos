/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsiglerp
 */
public class ClienteMulticast extends Thread {
    int MULTICAST_PORT = 6868;
    String IP_MULTICAST = "239.192.0.1";
    
    MulticastSocket socket;
    InetAddress direccion;
    
    Cliente cliente;
    
    public ClienteMulticast(Cliente cliente) {
        this.cliente = cliente;
        try {
            direccion = InetAddress.getByName(IP_MULTICAST);
            socket = new MulticastSocket(MULTICAST_PORT);
            socket.joinGroup(direccion);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteMulticast.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        while (true) {
            boolean ganador = false;
            while (!ganador) {
                try {
                    byte[] buffer = new byte[1000];
                    DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                    socket.receive(messageIn);

                    String mensaje = (new String(messageIn.getData())).trim();
                    System.out.println(mensaje);
                    String arr[] = mensaje.split(":");
                    if (arr[0].equals("W")) {
                        ganador = true;
                        cliente.reportWinner(arr[1]);
                    } else if (arr[0].equals("P")) {
                        cliente.cambiaTopo((int) Integer.parseInt(arr[1]));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClienteMulticast.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
