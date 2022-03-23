package procesos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProcesoA {

    public ProcesoA(){}
    public static void main(String[] args){
        Socket s = null;
        String message="Holita";
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out =
                    new DataOutputStream(s.getOutputStream());
            System.out.println("Sent: " + message);
            out.writeUTF(message);
            String data = in.readUTF();
            System.out.println("Received: " + data);

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
