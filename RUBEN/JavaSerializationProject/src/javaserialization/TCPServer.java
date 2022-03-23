package javaserialization;

import java.net.*;
import java.io.*;


public class TCPServer {

	public static void main(String args[]) {
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				System.out.println("Waiting for connections...");
				Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
				Connection c = new Connection(clientSocket);
				c.start();
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}

}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {                           // an echo server
			try {
				Person person = (Person) in.readObject();
				System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
				person.setName("New "+person.getName());
				out.writeObject(person);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}




