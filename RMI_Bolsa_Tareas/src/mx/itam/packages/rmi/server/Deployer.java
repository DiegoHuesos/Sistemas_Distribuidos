package mx.itam.packages.rmi.server;
import mx.itam.packages.rmi.interfaces.Bioinformatics;
import mx.itam.packages.rmi.interfaces.DataMining;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Deployer {
    public static void main(String [] args){
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmi/server/server.policy");

        if (System.getSecurityManager() == null){
            System.setSecurityManager( new SecurityManager() );
        }


        try{
            String serverAdrress = "localhost";
            System.setProperty("java.rmi.server.hostname", serverAdrress);



            // start the rmiregistry
            LocateRegistry.createRegistry(1099);   // default port
            // if the rmiregistry is not started by using java code then
            // 1) Start it as follows: rmiregistry -J-classpath -J"c:/.../RMI.jar" or
            // 2) Add this to the classpath C:\...\RMI.jar and then start the rmiregistry

            String bioService = "Bioinformatics", dataService = "DataMining", imageService = "ImageProcessing";
            SlaveNode bioEngine = new SlaveNode();
            Bioinformatics bioStub = (Bioinformatics) UnicastRemoteObject.exportObject(bioEngine, 0);

            SlaveNode dataEngine = new SlaveNode();
            DataMining dataStub = (DataMining) UnicastRemoteObject.exportObject(dataEngine, 0);

            SlaveNode imageEngine = new SlaveNode();
            Bioinformatics imageStub = (Bioinformatics) UnicastRemoteObject.exportObject(imageEngine, 0);

            Registry registry = LocateRegistry.getRegistry();

            registry.rebind(bioService, bioStub);
            System.out.println("Bioinformatics bound");

            registry.rebind(dataService, dataStub);
            System.out.println("DataMining bound");

            registry.rebind(imageService, imageStub);
            System.out.println("ImageProcessing bound");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
