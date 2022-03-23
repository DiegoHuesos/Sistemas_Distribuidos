/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class Starter {

    public static void main(String[] args) {
        try {
            //Políticas de seguridad del servidor
            
            System.setProperty("java.security.policy","file:/Users/alineitzelbecerracarranza/Desktop/Distribuidos/Repaso Primer Parcial/Todas/JavaBoTs/server/server.policy");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            //Arranca el RMI Registry
            LocateRegistry.createRegistry(1099);
            
            //Crear instancias de esclavos
            SlaveNode Bioinformatics = new SlaveNode("BioInformatics");
            SlaveNode DataMining = new SlaveNode("DataMining");
            SlaveNode ImageProcessing = new SlaveNode("ImageProcessing");
            
            //Despliegue de los servicios
            Bioinformatics.despliegue();
            DataMining.despliegue();
            ImageProcessing.despliegue();
            
            System.out.println("Se han desplegado los tres servicios");
        } catch (RemoteException ex) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
