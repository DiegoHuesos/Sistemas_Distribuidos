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
import sun.management.snmp.jvmmib.EnumJvmRTBootClassPathSupport;

import java.util.Enumeration;

public class MessageReceiver {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    // Name of the queue we will receive messages from
    private String subject = "RRO_QUEUE";

    public MessageReceiver(String subject){
        this.subject=subject;
    }
    public Papa getMessages() {

        boolean goodByeReceived = false;

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(subject);

            QueueBrowser browser=session.createBrowser((Queue) destination);
            Enumeration messageEnumeration=browser.getEnumeration();

            Papa papa=null;
            if(messageEnumeration!=null && messageEnumeration.hasMoreElements()){
                MessageConsumer messageConsumer = session.createConsumer(destination);
                ObjectMessage objectMessage = (ObjectMessage) messageConsumer.receive();
                papa=(Papa) objectMessage.getObject();
                messageConsumer.close();
            }

            session.close();
            connection.close();

            return papa;

        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }

    //public static void main(String[] args) {
    //    new MessageReceiver().getMessages();
    //}

}
