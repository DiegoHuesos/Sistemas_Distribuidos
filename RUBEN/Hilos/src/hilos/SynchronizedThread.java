package hilos;

public class SynchronizedThread extends Thread {

    Counter counter;
    public SynchronizedThread(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run() {
        counter.increaseAndPrint(Thread.currentThread().getName());
    }
}
