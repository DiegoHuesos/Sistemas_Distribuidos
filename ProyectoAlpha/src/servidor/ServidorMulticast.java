package servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorMulticast extends Thread{
    private final int MULTICAST_PORT = 6868;
    private final String IP_MULTICAST = "239.192.0.1"; //"225.228.225.228";
    private Juego guacamole;
    private MulticastSocket socket;
    private InetAddress direccion;

    private final int CHANGE_POS_TIME = 1500;

    public ServidorMulticast (Juego g) {
        this.guacamole = g;
    }

    public void despliega() {
        try {
            this.socket = new MulticastSocket(MULTICAST_PORT);
            this.direccion = InetAddress.getByName(IP_MULTICAST);
            socket.joinGroup(direccion);
            System.out.println("¡Multicast desplegado!");
            this.start();
        } catch (IOException ex) {
            System.out.println("Servidor Multicast: Problemas para unirse al grupo.");
            Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            while (!guacamole.getStatus()) {
                // Enviamos la posicion del topo
                guacamole.mueveTopo();
                String mensaje = "P:" + guacamole.obtenPosicion();
                byte[] m = mensaje.getBytes();
                DatagramPacket messageOut = new DatagramPacket(m, m.length, direccion, MULTICAST_PORT);
                try {
                    socket.send(messageOut);
                    // A mimir
                    Thread.sleep(CHANGE_POS_TIME);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Ya termino, alguien gano
            // Necesitamos reportar quien gano
            String mensaje = "W:" + guacamole.getWinner();
            System.out.println("JUEGO TERMINADO");
            byte[] m = mensaje.getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, direccion, MULTICAST_PORT);

            guacamole.restart(); // Antes, borramos el juego ya.
            try {
                socket.send(messageOut);
                // A mimir
                Thread.sleep(CHANGE_POS_TIME); // Nos mimimos otra vez
            } catch (IOException ex) {
                Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServidorMulticast.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
