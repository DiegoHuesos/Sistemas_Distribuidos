/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacion.Juego;
import comunicacion.Monstruo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alineitzelbecerracarranza
 */

public class ServidorMulticast extends Thread{
    
    private Partida partida;
    private int celdas;
    private double segundos;
    private int puerto;
    private MulticastSocket socketMulticast;
    private InetAddress direccion;
    
    public ServidorMulticast (Partida partida, Juego datos) {
        try {
            this.partida=partida;
            this.celdas=datos.getNumeroCeldas();
            this.segundos=3;
            this.puerto=datos.getPuertoMul();
            this.socketMulticast = new MulticastSocket(puerto);
            this.direccion=InetAddress.getByName(datos.getDireccionMul());
            
            socketMulticast.joinGroup(direccion);
        } catch (IOException ex) {
            System.out.println("ServidorM: Problemas para unirse al grupo.");
            //Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        try {
            while(true){
                while (partida.getGanador().equals("")) {
                    //Enviar monstruo con el tiempo que esperará
                    Random rand = new Random();
                    int celda=rand.nextInt(celdas);
                    Monstruo monstruo = new Monstruo(celda);
                    //Modifico valor en celda para que con TCP se pueda verificar
                    partida.setCeldaMonstruo(celda);
                    //System.out.println("ServidorM: "+celda);
                    //Mando la posicion del mostruo por multicast
                    String mensaje = monstruo.toString()+" "+"fin";
                    byte[] m = mensaje.getBytes();
                    DatagramPacket messageOut = new DatagramPacket(m, m.length, direccion, puerto);
                    socketMulticast.send(messageOut);
                    partida.tiempoM=System.currentTimeMillis();
                    try {
                        Thread.sleep((int)segundos*1000); //Sólo para que no me lo esté mandando a cada rato
                    } catch (InterruptedException ex) {
                        System.out.println("ServidorM: Problemas para dormir el hilo.");
                        //Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //Avisa del ganador
                //Enviar monstruo con el tiempo que esperará
                String mensaje = "Ganador "+partida.getGanador()+" fin";
                byte[] m = mensaje.getBytes();
                DatagramPacket messageOut = new DatagramPacket(m, m.length, direccion, puerto);
                socketMulticast.send(messageOut);
                System.out.println("ServidorM: "+mensaje);
                System.out.println("---");
                //partida.puntuacionesTodos();

                //Espera tantito y reinicia el juego
                try {
                    Thread.sleep(2*1000); //Sólo para que no me lo esté mandando a cada rato
                    System.out.println("ServidorM: Se ha reiniciado");
                } catch (InterruptedException ex) {
                    System.out.println("ServidorM: Problemas para dormir el hilo.");
                    //Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
                }
                partida.reiniciar();
                
            }
           
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socketMulticast != null)
                socketMulticast.close();
        }
        
    }
         
}
