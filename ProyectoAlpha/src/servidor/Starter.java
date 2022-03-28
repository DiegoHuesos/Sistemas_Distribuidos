package servidor;

import estres.Globals;

public class Starter {

    public static void main(String[] args) {
        // Tiene que iniciar los servidores RMI, Multicast, TCP
        // Tiene que tener el juego
        System.setProperty("sun.net.maxDatagramSockets", "500");
        System.setProperty("java.net.preferIPv4Stack", "true"); // Para el multicast

        int n = Globals.topos;
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
