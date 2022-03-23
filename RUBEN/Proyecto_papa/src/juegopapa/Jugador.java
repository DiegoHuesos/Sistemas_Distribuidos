package juegopapa;

import jmsqueue.MessageReceiver;
import jmsqueue.MessageSender;

import java.util.Random;

public class Jugador extends Thread {

    String name;
    Papa papa;
    String oponent;
    MessageReceiver receiver;
    MessageSender sender;
    public Jugador(String name, String oponent) {
        this.name = name;
        this.oponent=oponent;
    }

    @Override
    public void run() {
        Random rand=new Random();
        Papa papa = new Papa(name,rand.nextInt(10)+1);
        sender=new MessageSender(name);
        receiver=new MessageReceiver(oponent);
        boolean finished=false;
        while(!finished){
            sender.produceMessages(papa);
            papa=receiver.getMessages();
            if(papa==null){
                System.out.println("Jugador "+name+" ganó");
                finished=true;
            }
            else if(papa.touch()){
                System.out.println("Al jugador "+name+" se le cayó la papa");
                finished=true;
            }
        }
    }
}
