package procesos;

import interfaces.ProcesoRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProcesoC implements ProcesoRMI {

    public ProcesoC() throws RemoteException{
        super();
    }

    @Override
    public synchronized String alteraMensaje(String message) throws RemoteException {
        return message+"*****";
    }
    public static void main(String args[]){
        System.setProperty("java.security.policy","file:src/procesos/procesos.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name= "ProcesoRMI";
            LocateRegistry.createRegistry(1099);
            ProcesoC engine= new ProcesoC();
            Registry registry= LocateRegistry.getRegistry();
            ProcesoRMI stub = (ProcesoRMI) UnicastRemoteObject.exportObject(engine,0);
            registry.rebind(name,stub);
            System.out.println("Servicio RMI desplegado");
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
