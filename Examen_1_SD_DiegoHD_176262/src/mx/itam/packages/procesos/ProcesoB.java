package mx.itam.packages.procesos;

import mx.itam.packages.interfaces.ProcesoRMI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Diego Hernández Delgado (176262)

public class ProcesoB implements ProcesoRMI {

    public ProcesoB() throws RemoteException {
        super();
    }

    @Override
    public synchronized void enviaMensajeTCP_a_ProcesoC(String message) throws IOException {
        //Client TCP
        //Send TCP to ProcesoC

        int serverPort = 49150;

        Socket socket = new Socket("localhost", serverPort);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
        System.out.println("Mensaje recibido de ProcesoA: " + message);
        System.out.println("Mensaje enviado a ProcesoC: " + message);
    }


    @Override
    public synchronized void enviaMensajeUDP_a_ProcesoA(String message) throws IOException {
        //Client UDP
        //Send UDP to ProcesoA


        DatagramSocket aSocket = new DatagramSocket();
        byte[] m = message.getBytes();
        //InetAddress aHost = InetAddress.getByName("localhost");
        InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte) 127, (byte) 0, (byte) 0, (byte) 1});

        int serverPort = 49152;
        DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
        aSocket.send(request);

        System.out.println("Mensaje recibido de ProcesoC: " + message);
        System.out.println("Mensaje enviado a ProcesoA: " + message);
    }

    //RMI SERVER
    public static void main(String args[]){
        System.setProperty("java.security.policy","file:src/mx/itam/packages/client.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name= "ProcesoRMI";
            LocateRegistry.createRegistry(1099);
            ProcesoB engine= new ProcesoB();
            Registry registry= LocateRegistry.getRegistry();
            ProcesoRMI stub = (ProcesoRMI) UnicastRemoteObject.exportObject(engine,0);
            registry.rebind(name,stub);
            System.out.println("Proceso B: Servicio RMI desplegado");
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
