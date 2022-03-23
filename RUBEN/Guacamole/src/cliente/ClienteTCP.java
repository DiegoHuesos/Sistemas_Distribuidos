/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsiglerp
 */
public class ClienteTCP {
    int TCP_PORT = 7896;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Cliente cliente;
    
    public ClienteTCP(Cliente cliente) {
        this.cliente = cliente;
    }
    
    int golpeaTopo(int pos) {
        try {
            out.writeUTF(cliente.name + ":" + pos);
            int puntuacion = in.readInt();
            return puntuacion;
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }
    
    void conecta() {
        try {
            this.socket = new Socket("localhost", TCP_PORT);
            //   socket = new Socket("127.0.0.1", serverPort);    
            in = new DataInputStream( socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
