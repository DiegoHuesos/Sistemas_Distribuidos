package mx.itam.packages.procesos;

import mx.itam.packages.interfaces.ProcesoRMI;

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

//Diego Hernández Delgado (176262)

public class ProcesoC {


    //RECIBE MENSAJE TCP
    public static void main(String args[]){

        System.out.println("PROCESO_C");

        System.setProperty("java.security.policy","file:src/mx/itam/packages/client.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try{
            System.out.println("TCP SERVER INICIADO");
            int serverPort = 49150;
            try (ServerSocket listenSocket = new ServerSocket(serverPort)) {
                while (true) {
                    System.out.println("Waiting for TCP connection requests...");
                    Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                    Connection c = new Connection(clientSocket);
                    c.start();
                }
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
            System.out.println("Mensaje recibido del ProcesoB: " + data);

            String mensajeModificado = data + " ¡Oh! ¡Octavio!";


            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address
            String name= "ProcesoRMI";
            ProcesoRMI comp = (ProcesoRMI) registry.lookup(name);

            System.out.println("Saludos ");
            comp.enviaMensajeUDP_a_ProcesoA(mensajeModificado);
            System.out.println("Saludos 2");
            System.out.println("Mensaje enviado a  ProcesoB: " + mensajeModificado);

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