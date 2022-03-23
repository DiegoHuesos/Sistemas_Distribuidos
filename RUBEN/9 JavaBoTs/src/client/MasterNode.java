/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Task.Task;
import interfaces.BioInformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
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
    
    private static class MyThread extends Thread {
        private Task[] bolsa;
        private Registry registry;
        
        public MyThread(Task[] bolsa, Registry registry) {
            this.bolsa=bolsa;
            this.registry=registry;
        }
        
        public void run() {
            try { 
                //Obtenemos tipos de requerimiento
                String requerimiento=bolsa[1].getRequirement();

                if(requerimiento.compareTo("BioInformatics")==0){
                    BioInformatics comp = (BioInformatics) registry.lookup(requerimiento);
                    for (int i = 0; i < bolsa.length; i++) {
                        Task res = comp.executeBioTask(bolsa[i]);
                        System.out.println(res.toString());
                    }
                }else if(requerimiento.compareTo("DataMining")==0){
                    DataMining comp = (DataMining) registry.lookup(requerimiento);
                    for (int i = 0; i < bolsa.length; i++) {
                        Task res = comp.executeDataTask(bolsa[i]);
                        System.out.println(res.toString());
                    }
                }else{
                    ImageProcessing comp = (ImageProcessing) registry.lookup(requerimiento);
                    for (int i = 0; i < bolsa.length; i++) {
                        Task res = comp.executeImageTask(bolsa[i]);
                        System.out.println(res.toString());
                    }
                }
            
            } catch (RemoteException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        
        try {
            //Políticas de seguridad del cliente
            System.setProperty("java.security.policy","file:/C:/Users/sdist.ITAM/Documents/SD/JavaBoTs/src/client/client.policy");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            //Obtenemos el registro 
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            //Creación de cada bolsa
            Task[] bolsaBioinfo = new Task[15];
            Task[] bolsaData    = new Task[20];
            Task[] bolsaIma     = new Task[10];
            
            int[] tiempos={5000,10000,15000,20000,30000};
            
            //Para Bioinformatics
            for (int i = 0; i < 15; i++) {
                Task tarea = new Task("T"+(i+1),3,tiempos[i%5],0);
                bolsaBioinfo[i]=tarea;
            }
            //Para DataMining
            for (int i = 0; i < 20; i++) {
                Task tarea = new Task("T"+(i+11),2,tiempos[i%5],0);
                bolsaData[i]=tarea;
            }
            //Para ImageProcessing
            for (int i = 0; i < 10; i++) {
                Task tarea = new Task("T"+(i+31),2,tiempos[i%5],0);
                bolsaIma[i]=tarea;
            }
            
            //Crear los hilos de ejecución
            MyThread bolsaBio = new MyThread(bolsaBioinfo,registry);
            MyThread bolsaDat = new MyThread(bolsaData,registry);
            MyThread bolsaIm = new MyThread(bolsaIma,registry);
            
            //Iniciamos hilos ejecutores
            bolsaBio.start();
            bolsaDat.start();
            bolsaIm.start();
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
