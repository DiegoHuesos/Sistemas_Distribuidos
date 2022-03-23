package server;

import interfaces.Compute;
import interfaces.Credential;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeServer implements Compute {
    public static void main(String[] args){

        System.setProperty("java.security.policy","file:src/server/server.policy");
        //System.setProperty("java.rmi.server.hostname","192.168.56.1");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            LocateRegistry.createRegistry(1099);

            String name = "Compute";
            ComputeServer engine =new ComputeServer();
            Compute stub= (Compute) UnicastRemoteObject.exportObject(engine,0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name,stub);
            System.out.println("Servicio desplegado");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ComputeServer() throws RemoteException{
        super();
    }
    @Override
    public double square(int number, Credential aCredential) throws RemoteException {
        System.out.println(aCredential.toString());
        return number*number;
    }

    @Override
    public double power(int num1, int num2, Credential aCredential) throws RemoteException {
        System.out.println(aCredential.toString());
        return java.lang.Math.pow(num1,num2);
    }
}
