/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class ClienteRespuesta {
    
    private int puerto;
    private String direccion;
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    public ClienteRespuesta(int puerto, String direccion) {
        
        this.puerto = puerto;
        this.direccion = direccion;
        
    }
    
    public void enviaMensaje(String usuario, String respuesta){
        
        try {
            //Creación del tubo para la conexión. Aplano carretera
            this.s = new Socket(direccion, puerto);
            this.in = new DataInputStream( s.getInputStream());
            this.out = new DataOutputStream( s.getOutputStream());
            
            out.writeUTF(usuario+" "+respuesta+" "+"fin");
            //Recibo la respuesta del servidor
            String mensaje = in.readUTF();
            String arr[] = mensaje.split(" ");
            //System.out.println("Puntuacion de "+usuario+": "+ arr[0]);
            
            if(s!=null) 
            try {
                s.close(); //Cerramos socket para que no quede la conexión abierta
            } catch (IOException e){
                System.out.println("close:"+e.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteRespuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

}
