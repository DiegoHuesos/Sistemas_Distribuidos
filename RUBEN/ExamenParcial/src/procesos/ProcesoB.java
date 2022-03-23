package procesos;

import interfaces.ProcesoRMI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProcesoB {
    public static void main(String args[]){
        System.setProperty("java.security.policy","file:src/procesos/procesos.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
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
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out =new DataOutputStream(clientSocket.getOutputStream());
        } catch(IOException e)  {
            System.out.println("Connection:"+e.getMessage());
        }
    }

    @Override
    public void run(){
        try {
            String data;
            data = in.readUTF();
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            System.out.println("Cadena original: " + data);
            Registry registry = LocateRegistry.getRegistry("localhost");
            String name = "ProcesoRMI";
            ProcesoRMI comp = (ProcesoRMI) registry.lookup(name);
            String res = comp.alteraMensaje(data);
            System.out.println("Cadena alterada por proceso RMI: " + res);
            out.writeUTF(res);

        }catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
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

