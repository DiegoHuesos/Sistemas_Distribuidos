package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProcesoRMI extends Remote {
    public String alteraMensaje(String message) throws RemoteException;
}
