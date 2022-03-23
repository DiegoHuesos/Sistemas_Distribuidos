/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jcsiglerp
 */
public class ServidorTCP {
    int TCP_PORT = 7896;
    Juego guacamole;
    
    private class Connection extends Thread {
        DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        Juego guacamole;
        
	public Connection (Socket aClientSocket, Juego guacamole) {
            this.guacamole = guacamole;
            try {
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
        }
        
        @Override
        public void run() {
            try {			                 // an echo server
		while (true) {
                    String mensaje = in.readUTF();
                    String[] arr = mensaje.split(":");
                    String name = arr[0];
                    int pos = Integer.parseInt(arr[1]);
                    int puntuacion = guacamole.golpeJugador(name, pos);
                    //System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                    out.writeInt(puntuacion);
                }
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }
    
    public ServidorTCP(Juego guacamole) {
        this.guacamole = guacamole;
    }
    
    void despliega() {
        try{
		ServerSocket listenSocket = new ServerSocket(TCP_PORT);
		while(true) {
                        System.out.println("TCP esperando...");
                        Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			Connection c = new Connection(clientSocket, this.guacamole);
                        c.start();
		}
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
}
