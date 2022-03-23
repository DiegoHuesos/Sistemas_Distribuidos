package client;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

import javax.xml.crypto.Data;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class NewThread extends Thread{
    private Task[] bag;
    private Registry registry;

    public NewThread(Task[] bag, Registry registry){
        this.bag=bag;
        this.registry=registry;
    }

    @Override
    public void run() {
        try{
            //Get Requirement
            String requirement=bag[0].getRequirementId();
            //==Data Mining
            if(requirement.equals("DataMining")){
                DataMining comp=(DataMining) registry.lookup(requirement);
                for(int i=0; i< bag.length; i++){
                    Task resp= comp.executeDataTask(bag[i]);
                    System.out.println("DataMining task " + bag[i].getTaskId() + " Output: " + bag[i].getOutput());
                }
            }
            //==Bioinformatics
            else if(requirement.equals("Bioinformatics")){
                Bioinformatics comp=(Bioinformatics) registry.lookup(requirement);
                for(int i=0; i< bag.length; i++){
                    Task resp= comp.executeBioTask(bag[i]);
                    System.out.println("BioInformatics task " + bag[i].getTaskId() + " Output: " + bag[i].getOutput());
                }
            }
            //==Image Processing
            else{
                ImageProcessing comp=(ImageProcessing) registry.lookup(requirement);
                for(int i=0; i< bag.length; i++){
                    Task resp= comp.executeImageTask(bag[i]);
                    System.out.println("ImageProcessing task " + bag[i].getTaskId() + " Output: " + bag[i].getOutput());
                }
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
