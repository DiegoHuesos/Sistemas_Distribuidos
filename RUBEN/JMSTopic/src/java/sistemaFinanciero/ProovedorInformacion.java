/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaFinanciero;

import java.util.Random;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class ProovedorInformacion {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory; 
    
    public void produceMessages()  {
        
        Random r = new Random();
        TextMessage textMessage;  
        int numeroNoticias, categoria;
        
        try    {      
            Connection connection = connectionFactory.createConnection();      
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);      
            
            Topic[] topicos = {
                session.createTopic ("Telecomunicaciones"),
                session.createTopic ("Bancos"),
                session.createTopic ("Transporte"),
                session.createTopic ("Alimentos"),
                session.createTopic ("Educación")
            };
            
            MessageProducer[] messageProducers = {
                session.createProducer(topicos[0]),
                session.createProducer(topicos[1]),
                session.createProducer(topicos[2]),
                session.createProducer(topicos[3]),
                session.createProducer(topicos[4])
            }; 
                
            textMessage = session.createTextMessage();
            numeroNoticias = 15;
            
            for (int i = 0; i < numeroNoticias; i++) {
                categoria = r.nextInt(5);
                textMessage.setText("Nivel: "+r.nextInt(10)+
                                    " Categoría: "+ topicos[categoria].getTopicName());      
                System.out.println("Enviando una noticia terrible. "+
                        textMessage.getText());
                messageProducers[categoria].send(textMessage);
            }
            
            for (int i = 0; i < 5; i++) {
                textMessage.setText("Fin de la sesión para el topico "+
                        topicos[i].getTopicName());      
                System.out.println(textMessage.getText());
                messageProducers[i].send(textMessage);
                messageProducers[i].close();
            }
          
            session.close();      
            connection.close();    
        }    catch (JMSException e)    
        {      
            e.printStackTrace();    
        }  
    }  
    
    public static void main(String[] args)  {
        new ProovedorInformacion().produceMessages(); 
    }
    
}