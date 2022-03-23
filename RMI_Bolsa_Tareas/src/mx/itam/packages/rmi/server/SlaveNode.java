package mx.itam.packages.rmi.server;

import mx.itam.packages.rmi.interfaces.Bioinformatics;
import mx.itam.packages.rmi.interfaces.DataMining;
import mx.itam.packages.rmi.interfaces.ImageProcessing;
import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.System.getSecurityManager;


public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {

    public SlaveNode() throws RemoteException {
        super();
    }


    public String executeBioTask(Task task) throws RemoteException, InterruptedException {
        Thread.sleep(task.getLength()*1000);
        return "Ejecutado";
    }


    @Override
    public String executeDataMining(Task task) throws RemoteException, InterruptedException {
        Thread.sleep(task.getLength()*1000);
        return "Ejecutado";
    }

    @Override
    public String executeImageProcessing(Task task) throws RemoteException, InterruptedException {
        Thread.sleep(task.getLength()*1000);
        return "Ejecutado";
    }
}

