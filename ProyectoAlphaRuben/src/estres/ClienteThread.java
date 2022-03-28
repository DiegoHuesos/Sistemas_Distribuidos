/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estres;

import cliente.Cliente;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author padillandrea
 */
public class ClienteThread extends Thread {
    private Cliente cliente;
    private static int CLIENT_SLEEP_TIME = 100;
    
    public ClienteThread(String name) {
        cliente = new Cliente(name);
    }
    
    @Override
    public void run() {
        cliente.start();
        Random rand = new Random(); 
        while (cliente.isInGame()) {
          int x = rand.nextInt(Globals.topos); // tiros aleatorios
          cliente.golpeaTopo(x);
          try {
              Thread.sleep(CLIENT_SLEEP_TIME);
          } catch (InterruptedException ex) {
              Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);            
          }
        }
        System.out.println("RMI," + cliente.getName() + "," + cliente.getRmi().getRegisterTime());
        System.out.println("TCP-mean," + cliente.getName() + "," + Estresador.mean(cliente.getTcp().getResponseTime()));
        System.out.println("TCP-stdDev," + cliente.getName() + "," + Estresador.stdDev(cliente.getTcp().getResponseTime()));
        System.out.println("Errores-TCP," + cliente.getName() + "," + cliente.getTcp().getErroresTCP()/cliente.getTcp().getResponseTime().size());
        if(cliente.getRmi().isError()){
            System.out.println("Errores-RMI," + cliente.getName() + "," + 1);
        }else{
            System.out.println("Errores-RMI," + cliente.getName() + "," + 0);
        }
    }
}
