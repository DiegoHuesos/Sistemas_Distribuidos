package server;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {


    public SlaveNode() throws RemoteException{
        super();
    }

    public void deploy(String serviceType){

        try{
            SlaveNode engine= new SlaveNode();
            Registry registry= LocateRegistry.getRegistry();

            if(serviceType.equals("Bioinformatics")){
                Bioinformatics stub= (Bioinformatics) UnicastRemoteObject.exportObject(engine,0);
                registry.rebind(serviceType,stub);
            }
            else if (serviceType.equals("DataMining")){
                DataMining stub= (DataMining) UnicastRemoteObject.exportObject(engine,0);
                registry.rebind(serviceType,stub);
            }
            else{
                ImageProcessing stub= (ImageProcessing) UnicastRemoteObject.exportObject(engine,0);
                registry.rebind(serviceType,stub);
            }
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
    @Override
    public synchronized Task executeBioTask(Task aTask) throws RemoteException{
        try{
            Thread.sleep(aTask.getLength());
            aTask.setOutput(aTask.getLength()+"");
            System.out.println("Sevice "+aTask.getTaskId());
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return aTask;
    }

    @Override
    public synchronized Task executeDataTask(Task aTask) throws RemoteException{
        try{
            Thread.sleep(aTask.getLength());
            aTask.setOutput(aTask.getLength()+"");
            System.out.println("Sevice "+aTask.getTaskId());
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return aTask;
    }

    @Override
    public synchronized Task executeImageTask (Task aTask) throws RemoteException {
        try{
            Thread.sleep(aTask.getLength());
            aTask.setOutput(aTask.getLength()+"");
            System.out.println("Sevice "+aTask.getTaskId());
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return aTask;
    }
}
