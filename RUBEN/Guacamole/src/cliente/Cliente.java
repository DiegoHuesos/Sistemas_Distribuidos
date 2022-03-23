/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author jcsiglerp
 */
public class Cliente {
    String name;
    GameFrame frame;
    boolean inGame;
    
    ClienteRMI rmi;
    ClienteMulticast multicast;
    ClienteTCP tcp;
    
    public Cliente(GameFrame frame, String name) {
        this.frame = frame;
        this.name = name;
        this.inGame = false;
    }
    
    public int golpeaTopo(int i) {
        return tcp.golpeaTopo(i);
    }
    
    public void reportWinner(String winner) {
        frame.reportWinner(winner);
        rmi.registra();
    }
    
    public void cambiaTopo(int id) {
        frame.cambiaTopo(id);
    }
    
    public void start() {
        System.setProperty("java.net.preferIPv4Stack", "true"); // Para el multicast
        
        rmi = new ClienteRMI(this);
        multicast = new ClienteMulticast(this);
        tcp = new ClienteTCP(this);
        
        rmi.registra();
        if (!inGame) return;
        multicast.start();
        tcp.conecta();
    }
}
