/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class MasterNode {
    
    private class hiloEjeccucion extends Thread{
        
        
        Task[] tareas;
        Registry registry;

        public hiloEjeccucion(String t, Task[] tareas, Registry r) {
            this.tareas = tareas;
            this.registry = r;
        }
        
        public void run(){                
            try {
               
                
                if (tareas[0].getRequirementId().equals("Bioinformatics")){
                    Bioinformatics bio = (Bioinformatics) registry.lookup(tareas[0].getRequirementId());
                    for (int i =0; i<tareas.length; i++){
                        bio.excecuteBioTask(tareas[i]);
                        System.out.println("Tarea de Bioinformática ejecutada" + tareas[i].getTaskId() + " Salida: " + tareas[i].getOutput());
                    }
                } else {
                    if(tareas[0].getRequirementId().equals("DataMinig")){
                    DataMining dm = (DataMining) registry.lookup(tareas[0].getRequirementId());
                        for(int i =0; i< tareas.length; i++){
                            dm.excecuteDataTask(tareas[i]);
                            System.out.println("Tarea de Minería ejecutada" + tareas[i].getTaskId() + " Salida: " + tareas[i].getOutput());
                        }
                    } else{
                        ImageProcessing img = (ImageProcessing) registry.lookup(tareas[0].getRequirementId()); // establecer un vínculo
                        for(int i =0; i< tareas.length; i++){
                            img.excecuteImageTask(tareas[i]);
                            System.out.println("Tarea de Imágenes ejecutada" + tareas[i].getTaskId() + " Salida: " + tareas[i].getOutput());
                        }
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            }
    }}
    
    public static void main(String[] args) {
       
        // TODO code application logic here
        System.setProperty("java.security.policy","file:/C:\\Users\\sdist.ITAM\\Documents\\167028SD\\JavaBoTs\\src\\client\\client.policy");
        if (System.getSecurityManager() == null) {
            try {
                System.setSecurityManager(new SecurityManager());
                
                
                String name = "Compute";
                Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address

                
                Task[] imageTask = {new Task("T1", "Imagenes", 5000), new Task("T2", "Imagenes", 10000), new  Task("T3", "Imagenes", 15000), new  Task("T4", "Imagenes", 20000), new  Task("T5", "Imagenes", 30000),
                    new Task("T6", "Imagenes", 5000), new Task("T7", "Imagenes", 10000), new Task("T8", "Imagenes", 15000), new Task("T9", "Imagenes", 20000), new Task("T10", "Imagenes", 30000)};
                
                
                Task[] dataTask ={new Task("T11", "Mineria", 5000), new Task("T12", "Mineria", 10000), new  Task("T13", "Mineria", 15000), new  Task("T14", "Mineria", 20000), new  Task("T15", "Mineria", 30000),
                    new Task("T16", "Mineria", 5000), new Task("T17", "Mineria", 10000), new Task("T18", "Mineria", 15000), new Task("T19", "Mineria", 20000), new Task("T20", "Mineria", 30000),
                    new Task("T21", "Mineria", 5000), new Task("T22", "Mineria", 10000), new  Task("T23", "Mineria", 15000), new  Task("T24", "Mineria", 20000), new  Task("T25", "Mineria", 30000),
                    new Task("T26", "Mineria", 5000), new Task("T27", "Mineria", 10000), new Task("T28", "Mineria", 15000),new Task("T29", "Mineria", 20000), new Task("T30", "Mineria", 30000)};
                
                
                Task[] bioTask={ new Task("T31", "Bioinformatica", 5000), new Task("T32", "Bioinformatica", 10000), new  Task("T33", "Bioinformatica", 15000), new  Task("T34", "Bioinformatica", 20000), new  Task("T35", "Bioinformatica", 30000),
                    new Task("T36", "Bioinformatica", 5000), new Task("T37", "Bioinformatica", 10000), new Task("T38", "Bioinformatica", 15000), new Task("T39", "Bioinformatica", 20000), new Task("T30", "Bioinformatica", 30000),
                    new Task("T41", "Bioinformatica", 5000), new Task("T32", "Bioinformatica", 10000), new  Task("T33", "Bioinformatica", 15000), new  Task("T44", "Bioinformatica", 20000), new  Task("T45", "Bioinformatica", 30000)};
            } catch (RemoteException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         
        }
          
    }
    
}
