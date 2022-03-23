
package javaudpsockets;

import java.net.*;
import java.io.*;

public class UDPClient{
    
    public static void main(String args[]){ 
          DatagramSocket aSocket = null;
          try {
            aSocket = new DatagramSocket();
            String myMessage = "Hello";
            byte [] m = myMessage.getBytes();

            InetAddress aHost = InetAddress.getByName("localhost");
//               InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte)148, (byte)205, (byte)199 ,(byte) 100});
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + (new String(reply.getData())).trim()  );
          }
          catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
          }
          catch (IOException e){
            System.out.println("IO: " + e.getMessage());
          }
          finally{
            if(aSocket != null)
                aSocket.close();
          }
   }
}