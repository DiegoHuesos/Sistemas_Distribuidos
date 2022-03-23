/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import estres.Globals;

/**
 *
 * @author jcsiglerp
 */
public class Cliente {
    private String name;
    private GameFrame frame;
    private boolean inGame;
    
    private ClienteRMI rmi;
    private ClienteMulticast multicast;
    private ClienteTCP tcp;
    
    public Cliente(String name) {
        this.frame = null;
        this.name = name;
        this.inGame = false;
    }
    
    public Cliente(GameFrame frame, String name) {
        this.frame = frame;
        this.name = name;
        this.inGame = false;
    }
    
    public String getName() {
        return name;
    }
    
    public ClienteRMI getRmi() {
        return rmi;
    }

    public ClienteMulticast getMulticast() {
        return multicast;
    }

    public ClienteTCP getTcp() {
        return tcp;
    }
    
    public boolean isInGame() {
        return inGame;
    }
    
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public int golpeaTopo(int i) {
        return tcp.golpeaTopo(i);
    }
    
    public void reportWinner(String winner) {
        if (frame != null)
            frame.reportWinner(winner);
        inGame = false;
        tcp.desconecta();
        if(!Globals.debugMode) {
           this.start();
        }
    }
    
    public void cambiaTopo(int id) {
        if (frame != null)
            frame.cambiaTopo(id);
    }
    
    public void start() {
        System.setProperty("java.net.preferIPv4Stack", "true"); // Para el multicast
        
        rmi = new ClienteRMI(this);
        multicast = new ClienteMulticast(this, rmi.getIPMulticast(), rmi.getMulticastSocket());
        tcp = new ClienteTCP(this, rmi.getIPTCP(), rmi.getTCPSocket());
        
        rmi.registra();
        if (!inGame) return;
        multicast.start();
        tcp.conecta();
    }
}
