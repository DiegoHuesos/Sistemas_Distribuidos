package estres;

import cliente.Cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteThread extends Thread{

    private Cliente cliente;
    private static int CLIENT_SLEEP_TIME = 100;
    String rmiText;
    String tcpText;
    String tcpMeanText;
    String tcpStdDevText;
    String erroresTcpText;
    String text;
    Path filePath = Paths.get("./", "test.txt");

    public ClienteThread(String name) {
        cliente = new Cliente(name);
    }

    @Override
    public void run() {
        cliente.start();
        Random rand = new Random();
        while (cliente.isInGame()) {
            int x = rand.nextInt(Globals.topos); // tiros aleatorios
            cliente.hitTopo(x);
            try {
                Thread.sleep(CLIENT_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        rmiText = "RMI," + cliente.getName() + "," + cliente.getRmi().getRegisterTime();
        tcpText = "TCP," + cliente.getName() + "," + cliente.getTcp().getResponseTime().get(0);
        tcpMeanText = "TCP-mean," + cliente.getName() + "," + Estresador.mean(cliente.getTcp().getResponseTime());
        tcpStdDevText = "TCP-stdDev," + cliente.getName() + "," + Estresador.stdDev(cliente.getTcp().getResponseTime());
        erroresTcpText = "Errores-TCP," + cliente.getName() + "," + cliente.getTcp().getErroresTCP()/cliente.getTcp().getResponseTime().size();

        System.out.println(rmiText);
        System.out.println(tcpText);
        System.out.println(tcpMeanText);
        System.out.println(tcpStdDevText);
        System.out.println(erroresTcpText);



        if(cliente.getRmi().isError()){
            System.out.println("Errores-RMI," + cliente.getName() + "," + 1);
        }else{
            System.out.println("Errores-RMI," + cliente.getName() + "," + 0);
        }
    }

}
