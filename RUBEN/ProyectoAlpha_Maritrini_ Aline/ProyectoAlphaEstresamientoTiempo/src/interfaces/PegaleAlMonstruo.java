/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import comunicacion.Juego;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alineitzelbecerracarranza
 */

public interface PegaleAlMonstruo extends Remote{
    
    public Juego registroJuego(String usuario) throws RemoteException;
    
}
