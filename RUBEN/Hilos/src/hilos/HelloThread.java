package hilos;

public class HelloThread extends Thread{

    public void run(){
        for(int i=0; i<10000;i++)
            System.out.println(i+ ": Hola soy el thread: "+Thread.currentThread().getName());
    }
}
