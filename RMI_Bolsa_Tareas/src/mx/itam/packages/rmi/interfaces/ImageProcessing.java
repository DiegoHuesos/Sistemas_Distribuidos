package mx.itam.packages.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import mx.itam.packages.rmi.serializableobjects.Task;

public interface ImageProcessing extends Remote {

    // Calculate the square of a number
    public String executeImageProcessing ( Task task ) throws RemoteException, InterruptedException;

}

