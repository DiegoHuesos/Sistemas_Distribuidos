/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfaces.Registro;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author jcsiglerp
 */
public class ServidorRMI implements Registro {

    Juego guacamole;
    
    ServidorRMI(Juego g) {
        this.guacamole = g;
    }
    
    void despliega() {
        System.setProperty("java.security.policy","file:./src/servidor/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);

            String name = "RegistroGuacamole";

            Registro stub = (Registro) UnicastRemoteObject.exportObject(this, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("RMI desplegado!");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean registraJugador(String name) throws RemoteException {
        boolean registrado = guacamole.agregaJugador(name);
        if (registrado) {
            System.out.println(name + " registrado correctamente.");
        } else {
            System.out.println("Ya hay un " + name + " en la sala");
        }
        return registrado;
    }
    
}
