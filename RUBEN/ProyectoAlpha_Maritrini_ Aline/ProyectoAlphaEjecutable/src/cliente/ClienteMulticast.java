/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunicacion.Juego;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class ClienteMulticast extends Thread{
    
    private HashMap<Integer,JButton> celdas;
    private Juego juegoInfo;
    private int celdaActiva;
    private JButton ButtonReinicia, salir;
    private String usuario;
    private JLabel puntuacion;
    private MulticastSocket s;
    private boolean ganador;
    
    public ClienteMulticast(HashMap<Integer, JButton> celdas, Juego juegoInfo, 
                            JButton ButtonReinicia, String usuario, JLabel puntuacion, JButton salir) {
        this.celdas = celdas;
        this.juegoInfo = juegoInfo;
        this.celdaActiva=0;
        this.ButtonReinicia=ButtonReinicia;
        this.usuario=usuario;
        this.puntuacion=puntuacion;
        this.salir=salir;
        this.s = null;
        this.ganador=false;
    }
    
    public void run(){
        
        
        InetAddress grupo;
        try {
            grupo=InetAddress.getByName(juegoInfo.getDireccionMul());
            System.out.println(juegoInfo.getDireccionMul());
            s = new MulticastSocket(juegoInfo.getPuertoMul());
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn;
            
            while (!ganador) {
                s.joinGroup(grupo);
                //System.out.println("Esperando monstruos");
                messageIn= new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                String mensaje =(new String(messageIn.getData())).trim();
                String arr[] = mensaje.split(" ");
                //System.out.println("mensaje: "+mensaje);
                if(arr[0].equals("Ganador")){
                    ganador=true;
                    if(arr[1].equals(usuario)){
                        enviaMensaje("exito", "    ¡Felicidades has ganado el juego! \n "
                                + "\n ¿Quieres unirte a la siguiente partida?");
                        
                    }else{
                        enviaMensaje("fracaso", "      ¡Te ha ganado "+arr[1]+"! \n "
                                + "\n ¿Quieres unirte a la siguiente partida?");
                        
                    }
                }
                else{
                    //Mando llamar método TCP para que consteste
                    cambiaCelda(Integer.parseInt(arr[1]));
                    //respuestaTCP.enviaMensaje(usuario, arr[1]);
                }
                
            //Se manda llamar función de TCP para
            s.leaveGroup(grupo);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }finally{
            if (s != null)
                s.close();
        }
    }
    
    public void salJuego(){
        salir.doClick();
    }
    
    public void reiniciaJuego(){
        ganador=false;
        puntuacion.setText("0");
        ButtonReinicia.doClick();
    }
    
    public void cambiaCelda(int celda){
        cambiaImagen(celdas.get(celdaActiva),"vacio");
        cambiaImagen(celdas.get(celda),"conMonstruo");
        celdaActiva=celda;
    }
    
    private void cambiaImagen(JButton btn, String imagen){
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("imagenes/"+imagen+".png"));
        btn.setIcon(new ImageIcon(img.getImage().getScaledInstance((int)img.getIconWidth()/5, -1,
                java.awt.Image.SCALE_SMOOTH)));
    }
    
    public void enviaMensaje(String imagen, String mensaje ){
        
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("imagenes/"+imagen+".png"));
        ImageIcon icono=new ImageIcon(img.getImage().getScaledInstance(145, -1,
                java.awt.Image.SCALE_SMOOTH));
        
        if (JOptionPane.showConfirmDialog(null,mensaje, "¡El juego ha terminado!",
        JOptionPane.YES_NO_OPTION,0, icono) == JOptionPane.YES_OPTION) {
            ganador=false;
            puntuacion.setText("-");
        } else {
            if (s != null)
                s.close();
                System.exit(0);
        }
        
        
       
    }
    
}
