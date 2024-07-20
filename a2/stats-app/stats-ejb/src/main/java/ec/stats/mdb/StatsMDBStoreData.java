//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@MessageDriven(name = "StatsMDBStoreData", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class StatsMDBStoreData implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(StatsMDBStoreData.class.getName());

	public static final String SAVE_PATH = "C:\\enterprise\\tmp\\model\\stats.dat";
	
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
            	TextMessage textMessage = (TextMessage) message;
                String data = textMessage.getText();
                appendDataToFile(data, SAVE_PATH);
            	} catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    private void appendDataToFile(String data, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(data);
            System.out.println("a apended to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
