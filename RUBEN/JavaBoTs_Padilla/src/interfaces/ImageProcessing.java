/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;

/**
 *
 * @author sdist
 */
public interface ImageProcessing extends Remote{
    public Task excecuteImageTask(Task aTask);
    
}
