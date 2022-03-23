
package javatcpsockets;


import extras.AddressBook;

import java.net.*;
import java.io.*;


public class TCPServer {
    
    public static void main (String args[]) {
		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				System.out.println("Waiting for connection requests...");
				Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
				Connection c = new Connection(clientSocket);
				c.start();
			}
		} catch(IOException e) {
			System.out.println("Listen :"+ e.getMessage());}
		}
   
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	AddressBook addressBook;
	public Connection (Socket aClientSocket) {
	    try {

			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out =new DataOutputStream(clientSocket.getOutputStream());
			addressBook=new AddressBook();

	     } catch(IOException e)  {
	    	System.out.println("Connection:"+e.getMessage());
	    }
	}
        
        @Override
	public void run(){
	    try {			                 // an echo server
			//String data = in.readUTF();
			int data=in.readInt();
			while(data>0){
				String name=addressBook.getRecord(data).getName();
				System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
				//out.writeUTF(data);
				out.writeUTF(name);
				data=in.readInt();
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


