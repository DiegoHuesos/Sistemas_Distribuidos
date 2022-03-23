package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.Compute;
import interfaces.Credential;

public class ComputeClient {

    public static void main(String[] args){

        System.setProperty("java.security.policy","file:src/client/client.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String name= "Compute";
        try {
            Registry registry= LocateRegistry.getRegistry("localhost");
            Compute comp =(Compute) registry.lookup(name);
            Credential cred=new Credential(8,"rubs");
            System.out.println("3^2="+comp.square(3, cred));
            System.out.println("3^3="+comp.power(3,3,cred));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
         catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
