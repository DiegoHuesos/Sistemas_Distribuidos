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
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    // Name of the topic we will receive messages from
    private static String subject = "Telecommunications";

    public void getMessages() {

        boolean goodByeReceived = false;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(subject);

            MessageConsumer messageConsumer = session.createConsumer(destination);
            
            System.out.println("I'm a floor broker handling " + subject.toString());

            while (!goodByeReceived) {
                System.out.println("Waiting for messages...");
                TextMessage textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null) {
                    if( Integer.parseInt(textMessage.getText())<= 5 ){
                        System.out.println("I recived bad news of level: " + textMessage.getText() + "\n");
                        System.out.println("I have to be patient. There is no such thing as 'global economic crisis'");
                    } else {
                        }if (textMessage.getText().equals("Good bye!")){
                            goodByeReceived = true;
                        } else {
                            System.out.print("I recived bad news of level: " + textMessage.getText()+ "\n");
                            System.out.println("Selling! Selling! Selling!");
                        }
                }
                if (textMessage.getText() != null && textMessage.getText().equals("Good bye!")) {
                    goodByeReceived = true;
                }
            }

            messageConsumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessageReceiver().getMessages();
    }

}