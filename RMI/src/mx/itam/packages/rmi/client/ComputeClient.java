package mx.itam.packages.rmi.client;

import mx.itam.packages.rmi.interfaces.Compute;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.System.getSecurityManager;

public class ComputeClient {

    public static void main(String [] args) throws RemoteException, NotBoundException {

        System.setProperty("java.security.policy","src/mx/itam/packages/rmi/server/server.policy");

        if (getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "Compute";
        try{
            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address
            Compute comp = (Compute) registry.lookup(name);

            System.out.println("3^2 = " + comp.square(3));
            System.out.println("3^3 = " + comp.power(3, 3));

        }catch(RemoteException e){
            e.printStackTrace();
        }catch (NotBoundException e){
            e.printStackTrace();
        }




    }
}
