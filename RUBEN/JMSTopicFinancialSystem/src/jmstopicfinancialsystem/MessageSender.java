/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmstopicfinancialsystem;

/**
 *
 * @author JGUTIERRGARC
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String[] topics = {"Telecommunications","Banks","Transportation","FoodSupply","Education"};
   // Topic Name. You can create any/many topic names as per your requirement. 

    // default broker URL is : tcp://localhost:61616" // con esto se conecta
   // Queue Name. You can create any/many queue names as per your requirement. 

    public void produceMessages() {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
        
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url); // url del servidor al que nos estamos conectando
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
                
            for(int j =0; j<5; j++){
                
                int num_topic =(int)(Math.random() * 4) ;
                String topic = topics[num_topic];
                Destination destination = session.createTopic(topic);
                
                messageProducer = session.createProducer(destination);
                textMessage = session.createTextMessage();
                
                String categoria = String.valueOf((int)(Math.random() * 9) + 1);
                textMessage.setText(categoria);
                System.out.println("Sending the following message for " + topic +": " + textMessage.getText());
                messageProducer.send(textMessage); 
                
                if(j == 4 ){
                    for(int i =0; i<5; i++){
                    textMessage.setText("Good bye!");
                    System.out.println("Sending the following message: " + textMessage.getText());
                    messageProducer.send(textMessage);
                    }  
                    messageProducer.close();
                }
            }
             
            session.close();
            connection.close();   
   
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessageSender().produceMessages();
    }
}
