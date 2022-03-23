package jmstopicfinancsys;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Random;

public class MessageSender {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    private static String[] topics = {"Telecommunications","Banks","Transportation","FoodSupply","Education"};


    public void produceMessages(int nMessages) {
        MessageProducer messageProducer=null;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Random rand=new Random();

            TextMessage textMessage=session.createTextMessage();

            for(int i=0; i<nMessages; i++){
                int nTopic=rand.nextInt(topics.length);
                String topicTitle=topics[nTopic];
                Destination destination = session.createTopic(topicTitle);
                messageProducer = session.createProducer(destination);

                String level=(rand.nextInt(9)+1)+"";
                textMessage.setText(level);
                System.out.println("Sending terrible market news. Level: "+textMessage.getText()+" Category: " +topicTitle);
                messageProducer.send(textMessage);
            }

            for(int i=0; i<topics.length; i++){
                String topicTitle=topics[i];
                Destination destination = session.createTopic(topicTitle);
                messageProducer = session.createProducer(destination);

                textMessage.setText("Good bye!");
                System.out.println("Sending the following message on topic "+topicTitle+": " + textMessage.getText());
                messageProducer.send(textMessage);
            }

            messageProducer.close();
            session.close();
            connection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessageSender().produceMessages(15);
    }
}
