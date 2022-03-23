/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing{
    
    public SlaveNode(){
        super();
    }
    
    public void start(String servicio){
        
            // TODO code application logic here
            System.setProperty("java.security.policy","file:/C:\\Users\\sdist.ITAM\\Documents\\167028SD\\JavaBoTs\\src\\server\\server.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            SlaveNode engine = new SlaveNode();
            
           
            
        
    }

    @Override
    public Task excecuteBioTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Task excecuteDataTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Task excecuteImageTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
