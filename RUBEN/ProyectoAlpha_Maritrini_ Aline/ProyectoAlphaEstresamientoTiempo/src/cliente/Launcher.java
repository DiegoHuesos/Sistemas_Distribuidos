/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import interfaces.PegaleAlMonstruo;
import java.net.MulticastSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    private static final int numClientes = 400;      
    private static final String direccionRMI = "localhost";

    public static void main (String args[])  {
        
        //Para que jale multicast
        System.setProperty("sun.net.maxDatagramSockets", "500");
        System.setProperty("java.net.preferIPv4Stack", "true");
        
        System.setProperty("java.security.policy","file:C:\\Users\\sdist\\Downloads\\ProyectoAlphaEstresamientoTiempo\\src\\cliente\\client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(direccionRMI);
            
            
            for (int i=0; i<numClientes; i++){
                ClientThread clientThread = new ClientThread(""+i, registry);
                clientThread.start();
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
