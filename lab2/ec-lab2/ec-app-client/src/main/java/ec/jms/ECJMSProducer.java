package ec.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ECJMSProducer {
	private static final Logger logger = LogManager.getLogger(ECJMSProducer.class.getName()); 
    
	public static void main(String[] args) throws NamingException, JMSException {
        Connection connection = null;
        try {
            System.out.println("Create JNDI Context");
            Context context = ContextUtil.getInitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
            connection = connectionFactory.createConnection("quickstartUser", "quickstartPwd1!");
            
            System.out.println("Create session");
            Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Destination queue = (Destination) context.lookup("jms/queue/test");
            
            System.out.println("Start connection");
            connection.start();
            
            System.out.println("Create producer");
            MessageProducer producer = session.createProducer(queue);
            
            System.out.println("Create a message");
            Message msg = session.createTextMessage("EC lab jms");
            
            System.out.println("Send message");
            producer.send(msg);
            logger.info("message sent"); 

        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                connection.close();
            }
        }
        System.out.println("producer done");
    }
}