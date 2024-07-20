package ec.stats.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import ec.stats.sb.StatsSingleton;

import javax.inject.Inject;

@MessageDriven(name = "StatsMDBAddData",activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class StatsMDBAddData implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(StatsMDBAddData.class.getName());

    @Inject
    private StatsSingleton statsSessionBean;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
            	LOGGER.info("inside try");
                TextMessage textMessage = (TextMessage) message;
                String data = textMessage.getText();
                // Invoke session bean method to add data value to the array list
            	LOGGER.info("calling addData");
                statsSessionBean.addData((Double.parseDouble(data)));
            	LOGGER.info("after addData");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
