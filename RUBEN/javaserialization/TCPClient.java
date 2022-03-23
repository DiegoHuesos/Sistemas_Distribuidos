package javaserialization;

import java.net.*;
import java.io.*;

public class TCPClient {

    public static void main(String args[]) {

        Socket s = null;
        try {
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            Person me = new Person("Pedro","CDMX", 1999);
            out.writeObject(me);

            try {
                Person newMe = (Person) in.readObject();
                System.out.println(newMe);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

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
}
