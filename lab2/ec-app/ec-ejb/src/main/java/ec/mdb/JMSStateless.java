package ec.mdb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

@Stateless
@LocalBean
public class JMSStateless implements JMSStatelessLocal {
	
    @Inject
    JMSContext context;

    @Resource(lookup = "java:/queue/test")
    private Queue queue;
  
    @Resource(lookup = "java:/topic/test")
    private Topic topic;
	
    @Override
	public boolean produce(String mstr) {
		context.createProducer().send(queue, mstr);
		return true;
	}
	@Override
	public boolean publish(String mstr) {
		context.createProducer().send(topic, mstr);
		return false;
	}
}