/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunicacion.Juego;
import interfaces.PegaleAlMonstruo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class Cliente {

    private String usuario;
    private PegaleAlMonstruo serviciosJuego;
    
    public Cliente(String usuario, Registry registry ){
        try {
            this.usuario=usuario;
            this. serviciosJuego = (PegaleAlMonstruo ) registry.lookup("PegaleAlMonstruo");
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Juego conectaJuego(){
        Juego res = new Juego("Error",-1, "Error", -1);
        
        try {
            res = serviciosJuego.registroJuego(usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public void juega() {
        Juego juegoInfo = conectaJuego();
        
        
        //System.out.println(usuario+": Registro exitoso");
        ClienteRespuesta respuestaTCP = new ClienteRespuesta(juegoInfo.getPuertoTCP(), juegoInfo.getDireccionIPH());
        MulticastSocket s = null;
        InetAddress grupo;
        try {
            grupo=InetAddress.getByName(juegoInfo.getDireccionMul());
            byte[] buffer = new byte[1000];
            boolean ganador=false;
            s = new MulticastSocket(juegoInfo.getPuertoMul());
            DatagramPacket messageIn;

            while (!ganador) {
                s.joinGroup(grupo);
                //System.out.println("Esperando monstruos");
                messageIn= new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                String mensaje =new String(messageIn.getData()).trim();
                String arr[] = mensaje.split(" ");
                if(arr[0].equals("Ganador")){
                    ganador=true;
                    //System.out.println("Ganador "+arr[1]);
                }else{
                    //Mando llamar método TCP para que consteste
                    respuestaTCP.enviaMensaje(usuario, arr[1]);
                }
                s.leaveGroup(grupo);
            }

        }catch(SocketException so){
            System.out.println("Socket: "+so.getMessage());
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null)
                s.close();
        }

        

        
    }
    
}
