package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Starter {
    public static void main(String[] args){
        System.setProperty("java.security.policy","file:src/server/server.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);

            SlaveNode Bioinformatics = new SlaveNode();
            SlaveNode DataMining = new SlaveNode();
            SlaveNode ImageProcessing = new SlaveNode();

            Bioinformatics.deploy("Bioinformatics");
            DataMining.deploy("DataMining");
            ImageProcessing.deploy("ImageProcessing");

            System.out.println("Servicios desplegados");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
