package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registro extends Remote {

    public boolean registraJugador(String name) throws RemoteException;
    public String enviaIPTCP() throws RemoteException;
    public String enviaIPMulticast() throws RemoteException;
    public int enviaTCPSocket() throws RemoteException;
    public int enviaMulticastSocket() throws RemoteException;

}
