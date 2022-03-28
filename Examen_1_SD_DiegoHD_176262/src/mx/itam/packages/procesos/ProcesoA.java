package mx.itam.packages.procesos;

import mx.itam.packages.interfaces.ProcesoRMI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.System.getSecurityManager;

//Diego Hernández Delgado (176262)


public class ProcesoA {

    public static void main(String args[]) {

        System.out.println("PROCESO_A INICIADO");
        System.out.println("RMI CLIENT INICIADO");

        //RMI CLIENT --> ENVÍA MENSAJE A PROCESO_B
        try {
            String mensaje = "¡Qué maravilloso profesor!";

            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address
            String name= "ProcesoRMI";
            ProcesoRMI comp = (ProcesoRMI) registry.lookup(name);

            comp.enviaMensajeTCP_a_ProcesoC(mensaje);

            System.out.println("Mensaje enviado a ProcesoB: " + mensaje);
            System.out.println("RMI CLIENT FINALIZADO");

        }catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            System.out.println("IO:"+e.getMessage());
        }


        //UDP SERVER
        DatagramSocket aSocket = null;
        try {
            System.out.println("UDP SERVER INICIADO");
            int serverPort = 49152;
            aSocket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
            while (true) {
                System.out.println("Waiting for messages...");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                DatagramPacket reply = new
                        DatagramPacket(request.getData(),
                        request.getLength(),
                        request.getAddress(),
                        request.getPort());


                System.out.println("Datagram received");

                String sentence = new String( request.getData());
                System.out.println("Server received a request from " + request.getAddress());
                System.out.println("Mensaje recibido de ProcesoB:  " + sentence);

                System.out.println("UDP SERVER FINALIZADO");
                aSocket.send(reply);


            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }

}

