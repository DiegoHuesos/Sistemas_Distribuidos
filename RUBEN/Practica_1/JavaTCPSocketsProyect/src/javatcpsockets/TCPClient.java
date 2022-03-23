package javatcpsockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TCPClient {
    int numPeticiones;
    public TCPClient(int numPeticiones){
        this.numPeticiones=numPeticiones;
    }
    public void envioSolicitudes() {

        Socket s = null;
        int cont=this.numPeticiones;
        Random rand=new Random();
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out =
                    new DataOutputStream(s.getOutputStream());
            //out.writeUTF("Hello");            // UTF is a string encoding
            long [] times=new long[cont];
            for(int i=0;i<cont;i++) {
                long init=System.currentTimeMillis();
                int randomNum = rand.nextInt(4)+1;
                out.writeInt(randomNum);
                String data = in.readUTF();
                //System.out.println("Received: " + data);
                times[i]=System.currentTimeMillis()-init;
            }
            out.writeInt(-1);

            String res=stats(times);
            System.out.println(res);

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }
    public static String stats(long[] array){
        double mean=mean(array);
        double std=stdDev(array);

        return mean+","+std;
    }

    private static double mean(long[] list){
        double mean=0.0;
        for(int i=0;i<list.length;i++){
            mean+=list[i];
        }
        mean=mean/list.length;
        return mean;
    }

    private static double stdDev(long[] list){
        double sum = 0.0;
        double num= 0.0;
        for (int i=0; i< list.length; i++)
            sum+=list[i];
        double mean = sum/list.length;
        for (int i=0; i<list.length; i++)
            num+=Math.pow((list[i] -mean),2);
        return Math.sqrt(num/list.length);
    }
}
