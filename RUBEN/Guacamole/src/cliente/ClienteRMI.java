/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import interfaces.Registro;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsiglerp
 */
public class ClienteRMI {
    Cliente cliente;
    Registro reg;
    
    public ClienteRMI(Cliente cliente) {
        this.cliente = cliente;
        System.setProperty("java.security.policy","file:./src/cliente/client.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String name = "RegistroGuacamole";
        Registry registry = null; // server's ip address
        try {
            registry = LocateRegistry.getRegistry("localhost");
            reg = (Registro) registry.lookup(name);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    
    public void registra() {
        try {
            cliente.inGame = reg.registraJugador(cliente.name);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
