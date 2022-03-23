/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsqueue;

/**
 *
 * @author JGUTIERRGARC
 */
import javax.jms.*;

import juegopapa.Papa;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

    // A static manner to link jms resources with java objects
    //@Resource(mappedName = "jms/TestConnectionFactory")  
    //private static ConnectionFactory connectionFactory;  
    //@Resource(mappedName = "jms/TestQueue")  
    //private static Queue queue;  
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private String subject = "RRO_QUEUE"; // Queue Name. You can create any/many queue names as per your requirement.

    public MessageSender(String subject){
        this.subject=subject;
    }
    public void produceMessages(Papa papa) {
        MessageProducer messageProducer;
        try {

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);

            messageProducer = session.createProducer(destination);
            ObjectMessage objmessage = session.createObjectMessage();

            objmessage.setObject(papa);
            messageProducer.send(objmessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] args) {
    //    new MessageSender().produceMessages();
    //}
}
