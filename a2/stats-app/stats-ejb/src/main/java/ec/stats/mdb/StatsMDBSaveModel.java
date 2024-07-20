//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.inject.Inject;
import ec.stats.sb.StatsSingleton;
import ec.stats.StatsSummary;

@MessageDriven(name = "testQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class StatsMDBSaveModel implements MessageListener {
	private static final Logger LOGGER = Logger.getLogger(StatsMDBSaveModel.class.getName());

	@Inject
	private StatsSingleton statsSessionBean;

	@Override
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				try {
					TextMessage textMessage = (TextMessage) message;
					String msg = textMessage.getText();
					if (msg.equalsIgnoreCase("save")) {
						LOGGER.info("Saved");
						statsSessionBean.saveModel();

					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			
		}

	}
}
