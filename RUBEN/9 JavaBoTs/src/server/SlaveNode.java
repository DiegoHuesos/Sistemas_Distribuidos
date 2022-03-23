/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Task.Task;
import interfaces.BioInformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
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
public class SlaveNode implements BioInformatics, DataMining, ImageProcessing{
    private String tipoServicio;
    
    public SlaveNode() throws RemoteException {
        super();
    }
    
    public SlaveNode(String tipoServicio){
        this.tipoServicio=tipoServicio;
    }
    
    public void despliegue(){
        
        try {
            SlaveNode engine =new SlaveNode();
            Registry registry = LocateRegistry.getRegistry();
            
            if(tipoServicio.compareTo("BioInformatics")==0){
                BioInformatics stub = (BioInformatics) UnicastRemoteObject.exportObject(engine, 0);
                registry.rebind(tipoServicio, stub); 
            }else if(tipoServicio.compareTo("DataMining")==0){
                DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
                registry.rebind(tipoServicio, stub); 
            }else{
                ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
                registry.rebind(tipoServicio, stub); 
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Task executeBioTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
            task.setOutput(task.getLength());
            System.out.println("Servicio proporcionado a "+task.getTaskId());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }

    @Override
    public Task executeDataTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
            task.setOutput(task.getLength());
            System.out.println("Servicio proporcionado a "+task.getTaskId());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }

    @Override
    public Task executeImageTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
            task.setOutput(task.getLength());
            System.out.println("Servicio proporcionado a "+task.getTaskId());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }

}
