package mx.itam.packages.rmi.server;

import  mx.itam.packages.rmi.interfaces.Compute;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.System.getSecurityManager;


public class ComputeServer implements Compute {
    public ComputeServer() throws RemoteException {
        super();
    }

    @Override
    public double square(int number) throws RemoteException {
        return (number * number);
    }

    @Override
    public double power(int num1, int num2) throws RemoteException {
        return (java.lang.Math.pow(num1, num2));
    }


    //MAIN//

    public static void main(String [] args) throws RemoteException {
        System.setProperty("java.security.policy","src/mx/itam/packages/rmi/server/server.policy");

        if (getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try{
            LocateRegistry.createRegistry(1099);

            String name = "Compute";
            ComputeServer engine = new ComputeServer();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

        }catch ( RemoteException e){
            e.printStackTrace();
        }


    }
}

