package client;


import interfaces.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MasterNode {

    public static void main(String[] args){
        System.setProperty("java.security.policy","file:src/client/client.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry= LocateRegistry.getRegistry("localhost");

            Task[] bolsaBioInfo=new Task[15];
            Task[] bolsaDataMining=new Task[20];
            Task[] bolsaImageProcessing=new Task[10];

            int[] times={5000,10000,15000,20000,30000};

            //Bioinformatics
            for (int i = 0; i < 15; i++) {
                Task task = new Task("T"+(i+1),"Bioinformatics",times[i%5],"Success");
                bolsaBioInfo[i]=task;
            }
            //DataMining
            for (int i = 0; i < 20; i++) {
                Task task = new Task("T"+(i+11),"DataMining",times[i%5],"Success");
                bolsaDataMining[i]=task;
            }
            //ImageProcessing
            for (int i = 0; i < 10; i++) {
                Task task = new Task("T"+(i+31),"ImageProcessing",times[i%5],"Success");
                bolsaImageProcessing[i]=task;
            }

            NewThread bio= new NewThread(bolsaBioInfo,registry);
            NewThread dM= new NewThread(bolsaDataMining,registry);
            NewThread iP= new NewThread(bolsaImageProcessing,registry);

            //start
            bio.start();
            dM.start();
            iP.start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
