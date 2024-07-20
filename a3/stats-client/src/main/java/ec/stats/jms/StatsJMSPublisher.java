package ec.stats.jms;

import javax.jms.DeliveryMode;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;

public class StatsJMSPublisher {
static TopicConnection connection = null;
    
    public static void main(String[] args) throws Exception {
    	if (args.length != 2 || !args[0].equals("-message")) {
            System.out.println("Usage: java StatsJMSProducer -message <message>");
            return;
        }
        System.out.println("Create JNDI Context");
        Context context = ContextUtil.getInitialContext();
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        connection = connectionFactory.createTopicConnection("quickstartUser", "quickstartPwd1!");
        
        System.out.println("Create topic session");
        TopicSession topicSession = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        
        Topic topic = (Topic) context.lookup("jms/topic/test");
        
        System.out.println("Start topic connection");
        connection.start();
                               
       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                                                              
       String txtMsg = args[1];
       TextMessage message = topicSession.createTextMessage();
       message.setText(txtMsg);
                          
       topicPublisher.publish(message);
       System.out.println("Message published: " + message.getText());
       connection.close();
    }
}
