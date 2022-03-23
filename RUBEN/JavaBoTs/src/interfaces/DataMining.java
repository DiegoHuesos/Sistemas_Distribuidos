package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataMining extends Remote {
    public Task executeDataTask(Task aTask) throws RemoteException;
}
