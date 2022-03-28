package mx.itam.packages.interfaces;


import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


//Diego Hernández Delgado (176262)

public interface ProcesoRMI extends Remote {

    public void enviaMensajeTCP_a_ProcesoC(String message) throws IOException;

    public void enviaMensajeUDP_a_ProcesoA(String message) throws IOException;

}
