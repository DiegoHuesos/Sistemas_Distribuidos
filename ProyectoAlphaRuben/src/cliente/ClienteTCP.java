/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import estres.Globals;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsiglerp
 */
public class ClienteTCP {
//    private final int TCP_PORT = 7896;
//    private final String IP_TCP = "localhost";
    private int TCP_PORT;
    private String IP_TCP;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Cliente cliente;
    private int erroresTCP;
    private List<Long> responseTime = new ArrayList<>();
    
    public ClienteTCP(Cliente cliente, String IP_TCP, int TCP_PORT) {
        this.cliente = cliente;
        this.IP_TCP = IP_TCP;
        this.TCP_PORT = TCP_PORT;
        this.erroresTCP = 0;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
        
    public int getErroresTCP() {
        return erroresTCP;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Long> getResponseTime() {
        return responseTime;
    }
    
    public int golpeaTopo(int pos) {
        long initTime = System.currentTimeMillis();
        try {
            out.writeUTF(cliente.getName() + ":" + pos);
            int puntuacion = in.readInt();
            if(Globals.debugMode) responseTime.add(System.currentTimeMillis() - initTime);
            return puntuacion;
        } catch (IOException ex) {
            this.erroresTCP++;
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }
    
    public void conecta() {
        try {
            this.socket = new Socket(IP_TCP, TCP_PORT);
            //   socket = new Socket("127.0.0.1", serverPort);    
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconecta(){
        try{
            out.writeUTF(cliente.getName() + ":kill");
            in.readUTF();
        }catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
