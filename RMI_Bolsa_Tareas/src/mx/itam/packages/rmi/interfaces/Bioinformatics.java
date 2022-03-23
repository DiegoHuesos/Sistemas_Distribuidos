package mx.itam.packages.rmi.interfaces;

import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Bioinformatics extends Remote {

    // Calculate the square of a number
    public String executeBioTask (Task task ) throws RemoteException, InterruptedException;

}

