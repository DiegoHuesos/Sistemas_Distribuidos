/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jcsiglerp
 */
public interface Registro extends Remote {
    public boolean registraJugador(String name) throws RemoteException;
}
