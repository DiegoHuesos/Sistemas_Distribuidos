package mx.itam.packages.rmi.client;

import mx.itam.packages.rmi.interfaces.Bioinformatics;
import mx.itam.packages.rmi.interfaces.DataMining;
import mx.itam.packages.rmi.interfaces.ImageProcessing;
import mx.itam.packages.rmi.serializableobjects.Task;

import java.io.ObjectInputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.System.getSecurityManager;

public class MasterNode {


    public static void main(String [] args) throws RemoteException, NotBoundException {

        System.setProperty("java.security.policy","src/mx/itam/packages/rmi/server/server.policy");

        if (getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }


        try{

            Task[] procImag = {
                    new Task("T1", "Imagenes", 5), new Task("T2", "Imagenes", 10),
                    new Task("T3", "Imagenes", 15), new Task("T4", "Imagenes", 20),
                    new Task("T5", "Imagenes", 30), new Task("T6", "Imagenes", 5),
                    new Task("T7", "Imagenes", 10), new Task("T8", "Imagenes", 15),
                    new Task("T9", "Imagenes", 20), new Task("T10", "Imagenes", 30)};


            Task[] minDat = {
                    new Task("T11", "Mineria", 5), new Task("T12", "Mineria", 10),
                    new Task("T13", "Mineria", 15), new Task("T14", "Mineria", 20),
                    new Task("T15", "Mineria", 30), new Task("T16", "Mineria", 5),
                    new Task("T17", "Mineria", 10), new Task("T18", "Mineria", 15),
                    new Task("T19", "Minería", 20), new Task("T20", "Mineria", 30),
                    new Task("T21", "Mineria", 5), new Task("T22", "Mineria", 10),
                    new Task("T23", "Mineria", 15), new Task("T24", "Mineria", 20),
                    new Task("T25", "Mineria", 30), new Task("T26", "Mineria", 5),
                    new Task("T27", "Mineria", 10), new Task("T28", "Mineria", 15),
                    new Task("T29", "Minería", 20), new Task("T30", "Mineria", 30),
            };
            Task[] bioInfor = {
                    new Task("T31", "Bioinformatica", 5),new Task("T32", "Bioinformatica", 10), new Task("T33", "Bioinformatica", 15),
                    new Task("T34", "Bioinformatica", 20), new Task("T35", "Bioinformatica", 30), new Task("T36", "Bioinformatica", 5),
                    new Task("T37", "Bioinformatica", 10), new Task("T38", "Bioinformatica", 15), new Task("T39", "Bioinformatica", 20),
                    new Task("T40", "Bioinformatica", 30), new Task("T41", "Bioinformatica", 5), new Task("T42", "Bioinformatica", 10),
                    new Task("T43", "Bioinformatica", 15), new Task("T44", "Bioinformatica", 20),new Task("T45", "Bioinformatica", 30)
            };

            String serverAddress = "localhost";
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address

            Bioinformatics bioinformatics = (Bioinformatics) registry.lookup("bio_informatics");
            DataMining dataMining = (DataMining) registry.lookup("data_mining");
            ImageProcessing imageProcessing = (ImageProcessing) registry.lookup("image_processing");

            BioInformaticsThread bio = new BioInformaticsThread(bioInfor, bioinformatics);
            DataMiningThread data = new DataMiningThread(minDat, dataMining);
            ImageProcessingThread img = new ImageProcessingThread(procImag, imageProcessing);


            long inicio = System.currentTimeMillis();
            bio.start();
            data.start();
            img.start();
            bio.join();
            data.join();
            img.join();
            long fin = System.currentTimeMillis();

            long time = (fin - inicio)/1000;

            System.out.println("Time: " + time + "segundos.");

        }catch(RemoteException e){
            e.printStackTrace();
        }catch (NotBoundException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class BioInformaticsThread extends Thread{
    Task[] tasks;
    Bioinformatics bio;

    public BioInformaticsThread(){}
    public BioInformaticsThread(Task[] tasks, Bioinformatics bio){
        this.tasks = tasks;
        this.bio = bio;
    }

    public void run(Bioinformatics inforBio, Task[] bioInfor) throws RemoteException, InterruptedException {
        for(Task task: bioInfor){
            inforBio.executeBioTask(task);
        }
    }
}


class DataMiningThread extends Thread{
    private  Task[] tasks;
    private DataMining data;

    public DataMiningThread(){}
    public DataMiningThread(Task[] tasks, DataMining data){
        this.tasks = tasks;
        this.data = data;
    }
    public void run(DataMining datMin, Task[] datMinTasks) throws RemoteException, InterruptedException {
        for(Task task: datMinTasks){
            datMin.executeDataMining(task);
        }
    }
}

class ImageProcessingThread extends Thread{
    private  Task[] tasks;
    private ImageProcessing img;
    public ImageProcessingThread(){}
    public ImageProcessingThread(Task[] tasks, ImageProcessing img){
        this.tasks = tasks;
        this.img = img;
    }

    public void run(ImageProcessing imageProc, Task[] imagProcTasks) throws RemoteException, InterruptedException {
        for(Task task: imagProcTasks){
            imageProc.executeImageProcessing(task);
        }
    }
}

