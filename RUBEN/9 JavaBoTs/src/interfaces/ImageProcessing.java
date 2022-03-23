/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Task.Task;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sdist
 */
public interface ImageProcessing extends Remote{
    
    public Task executeImageTask(Task task) throws RemoteException;

}
