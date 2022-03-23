package hilos;

public class Counter {
    private int count=0;

    public Counter(int count){
        this.count=count;
    }
    public void  increaseAndPrint(String hiloId){
        for(int i=0;i<1000;i++){
            synchronized (this){
                count++;
                System.out.println(count + " " + hiloId);
            }

        }
    }
}
