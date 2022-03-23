/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author jcsiglerp
 */
public class Starter {
    
    
    public static void main(String[] args) {
        // Tiene que iniciar los servidores RMI, Multicast, TCP
        // Tiene que tener el juego
        System.setProperty("java.net.preferIPv4Stack", "true"); // Para el multicast
        
        int n = 9;
        Juego guacamole = new Juego(n);
        ServidorRMI rmi = new ServidorRMI(guacamole);
        ServidorMulticast multicast = new ServidorMulticast(guacamole);
        ServidorTCP tcp = new ServidorTCP(guacamole);
        
        // Despliega los servidores
        rmi.despliega();
        multicast.despliega();
        tcp.despliega();
    }
    
}
