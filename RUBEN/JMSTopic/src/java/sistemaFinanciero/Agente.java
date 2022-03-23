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
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class Agente {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory") 
    private static ConnectionFactory connectionFactory;
    private Topic topico;
    private int id;
    
    public Agente(int id){
        this.id =id;
    }
    
    
    public void getMessages()  { 
        String[] topicos = {"Telecomunicaciones",
                            "Bancos","Transporte", 
                            "Alimentos", "Educación"};
        Random r = new Random();
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        boolean goodByeReceived = false;
        
        try {      
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Topic topico=session.createTopic(topicos[r.nextInt(5)]);
            messageConsumer = session.createConsumer(topico);
            connection.start(); 
            System.out.println("Soy el broker "+this.id+
                    " y escucho al topico de "+
                    topico.getTopicName());
            while (!goodByeReceived){
                textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null){
                    String[] splited = textMessage.getText().split("\\s+");
                    if(splited[0].equals("Fin")){
                        goodByeReceived = true;  
                        System.out.println("Ha finalizado la sesión.");
                    }
                    else{
                        System.out.print("Recibí una mala noticia del nivel: " +
                            splited[1]); 
                        System.out.println();
                        if(splited[1].compareTo("5")<=0){
                            System.out.println("Seré paciente. No hay crisis.");
                        }
                        else{
                            System.out.println("¡Voy a vender!");
                        }
                        System.out.println(); 
                    }
                        
                }   
            }     
            messageConsumer.close();    
            session.close();    
            connection.close(); 
            
        }    catch (JMSException e)    {  
            e.printStackTrace();   
        } 
    }  
    
    public static void main(String[] args)  {
        
        new Agente(0).getMessages(); 
    }
    
}