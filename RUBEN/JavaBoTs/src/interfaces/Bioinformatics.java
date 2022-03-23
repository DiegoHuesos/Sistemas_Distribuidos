package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bioinformatics extends Remote {
    public Task executeBioTask(Task aTask) throws RemoteException;
}
