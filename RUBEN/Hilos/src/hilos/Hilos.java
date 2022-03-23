package hilos;

public class Hilos {
    public static void main(String args[]){
        /*
        //18/01/21
        HelloThread h1 =new HelloThread();
        Thread h2= new Thread(new HelloRunnable());
        //System.out.println("Hilo principal "+Thread.currentThread().getName());
        h1.start();

        //20/01/21
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        try {
            h1.join(100); //Sincronizacion via barrera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Continua el main()");
        h2.start();
        */

        Counter counter= new Counter(0);
        SynchronizedThread h0 = new SynchronizedThread(counter);
        SynchronizedThread h1 = new SynchronizedThread(counter);
        h0.start();
        h1.start();
    }
}
