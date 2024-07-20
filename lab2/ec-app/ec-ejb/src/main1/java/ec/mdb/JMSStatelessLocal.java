package ec.mdb;

import javax.ejb.Local;

@Local
public interface JMSStatelessLocal {
	public boolean produce(String mstr);
	public boolean publish(String mstr);
}