package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote{

    public double square (int number, Credential aCredential) throws RemoteException;

    public double power( int num1, int num2, Credential aCredential) throws RemoteException;
}
