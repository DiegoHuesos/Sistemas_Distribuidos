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
public interface DataMining extends Remote{
    public Task excecuteDataTask(Task aTask);
    
}
