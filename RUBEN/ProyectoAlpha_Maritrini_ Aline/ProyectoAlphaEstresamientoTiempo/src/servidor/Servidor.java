/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacion.Juego;
import interfaces.PegaleAlMonstruo;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author alineitzelbecerracarranza
 */

public class Servidor implements PegaleAlMonstruo{
    
    private static Juego datosJuego;
    private static int puertoRMI;
    private static String nombreServicio;
    private static Partida partida;

    public Servidor(String direccionIPH, int puertoTCP, String direccionMul, int puertoMul){
        
        this.datosJuego= new Juego(direccionIPH,puertoTCP, direccionMul,puertoMul);
        this.puertoRMI=1099;
        this.nombreServicio = "PegaleAlMonstruo";
        this.partida = new Partida();
        
    }
   
    public static void main(String[] args) throws IOException {
        System.setProperty("java.rmi.server.hostname", "148.205.133.161");
        System.setProperty("java.net.preferIPv4Stack", "true");
        
        int puertoMul          = 6868;
        int puertoTCP          = 6869;
        String direccionIPH    = "148.205.133.161";
        String direccionMul    = "225.228.225.228";
        
        Servidor engine = new Servidor(direccionIPH, puertoTCP, direccionMul,puertoMul);
        //Desplegamos servicio RMI
        //Definimos las políticas oara el servidor
        System.setProperty("java.security.policy","file:./src/servidor/server.policy");

        if (System.getSecurityManager() == null) {
           System.setSecurityManager(new SecurityManager());
        }
        
        try {
            LocateRegistry.createRegistry(puertoRMI);  
            PegaleAlMonstruo stub =(PegaleAlMonstruo) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(nombreServicio, stub);
            System.out.println("Servidor: Se ha desplegado correctamente el servicio JavaRMI");
        } catch (Exception e) {
            System.err.println("Error en servidor");
            e.printStackTrace();
        }
         
        //Corremos parte de TCP y UDP
        ServidorMulticast servidorM = new ServidorMulticast(partida, engine.datosJuego);
        ServidorTCP       servidorT = new ServidorTCP(partida, engine.datosJuego);
        
        servidorM.start();
        servidorT.start();
    }
    
    
    @Override
    public Juego registroJuego(String usuario) throws RemoteException {
        
        if(!partida.registraUsuario(usuario)){
            //System.out.println("Servidor: Reingreso de "+usuario);
        }
        else{
            //System.out.println("Servidor: Registro exitosos de "+usuario);
        }
            
        return this.datosJuego;
    }

    

} 

