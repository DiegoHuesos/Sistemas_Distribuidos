/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.MulticastSocket;
import java.rmi.registry.Registry;



/**
 *
 * @author alineitzelbecerracarranza
 */
public class ClientThread extends Thread {
        private String usuario; 
        private Registry registry;

        public ClientThread (String usuario, Registry registry) {
            this.usuario=usuario;
            this.registry=registry;
        }

        @Override
        public void run(){
	    try {			                 
              Cliente client = new Cliente(this.usuario, this.registry);
              client.juega();
            } 
            catch(Exception e) {
                System.out.println(e);
	    } 
    }
}