/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacion.Juego;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class ServidorTCP extends Thread{
    private static Partida partida;
    private static int puerto;
    private static ServerSocket socketEntrada;
    
    public ServidorTCP (Partida partida, Juego datos){
        this.partida=partida;
        this.puerto=datos.getPuertoTCP();
        try {
            socketEntrada = new ServerSocket(puerto);
        } catch (IOException ex) {
            System.out.println("ServidorT: Problemas con el puerto del servidor.");
            //Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        while(true){
            try {
                Socket socketCliente = socketEntrada.accept();
                Connection c = new Connection(socketCliente, partida);
                c.start();
                //System.out.println("ServidorT: Esperando puntos de los usuarios...");
            } catch (IOException ex) {
                System.out.println("ServidorT: Problemas para establecer conexión con el usuario.");
                //Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class Connection extends Thread {
    Partida partida;
    DataInputStream entrada;
    DataOutputStream salida;
    Socket socketCliente;
    
    public Connection (Socket socketCliente, Partida partida) {
        try {
            this.socketCliente = socketCliente;
            entrada = new DataInputStream(socketCliente.getInputStream());
            salida =new DataOutputStream(socketCliente.getOutputStream());
            this.partida=partida;
         } catch(IOException e)  {
             System.out.println("Connection:"+e.getMessage());
         }
    }
    
    @Override
    public void run(){
        try {

            //Recibo el nombre del usuario y la celda que golpeó
            String mensaje = entrada.readUTF();
            String arr[] = mensaje.split(" ");
            String usuario = arr[0];
            int celda = Integer.parseInt(arr[1]);

            //Veo si se le da el punto o no 
            if(celda == partida.getCeldaMonstruo()){
                partida.agregaPunto(usuario);
                //System.out.println("ServidorT: Se le agrego punto a "+usuario+". Ahora cuenta con: "+partida.puntuacionUsuario(usuario));
            }
            else{
                //System.out.println("ServidorT: Te pasate de listo mi chavo. Mal "+usuario+ "Envío "+celda+ " era "+partida.getCeldaMonstruo());
            }
            System.out.println(System.currentTimeMillis()-partida.tiempoM);
            salida.writeUTF(""+partida.puntuacionUsuario(usuario)+" "+"fin");


        } catch (IOException ex) {
            System.out.println("ServidorT: Problemas en las recepción/salida de mensaje en TCP");
            //Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(socketCliente!=null){
                try {
                    socketCliente.close();
                    //System.out.println("ServidorT: Se cerró conexión");
                } catch (IOException e){
                    System.out.println("close:"+e.getMessage());
                }
            }
        }
    }
}
