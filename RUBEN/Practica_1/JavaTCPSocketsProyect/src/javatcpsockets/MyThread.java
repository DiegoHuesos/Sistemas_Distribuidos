package javatcpsockets;

public class MyThread extends Thread{
    int m;

    MyThread(int m){
        this.m=m;
    }

    @Override
    public void run() {
        TCPClient client=new TCPClient(m);
        client.envioSolicitudes();
    }
}
