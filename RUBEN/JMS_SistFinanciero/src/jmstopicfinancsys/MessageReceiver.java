package jmstopicfinancsys;

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

    public static void getMessages(String topic) {

        boolean goodByeReceived = false;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();


            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            connection.start();



            System.out.println("I'm a floor broker handling " + topic);
            System.out.println();

            while (!goodByeReceived) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage.getText() != null && textMessage.getText().equals("Good bye!")) {
                    goodByeReceived = true;
                }
                else if (textMessage != null) {
                    System.out.println("I received bad news of level: "+textMessage.getText());
                    if(Integer.parseInt(textMessage.getText())<5){
                        System.out.println("I have to be patient. There is no such thing as a 'global economic crisis'");
                    }else{
                        System.out.println("Selling! Selling! Selling!");
                    }
                    System.out.println();
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
        String[] topics = {"Telecommunications","Banks","Transportation","FoodSupply","Education"};

        getMessages(topics[2]);

    }

}
