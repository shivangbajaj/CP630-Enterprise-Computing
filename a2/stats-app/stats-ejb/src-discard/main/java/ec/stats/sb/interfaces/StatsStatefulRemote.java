package ec.stats.interfaces;

import javax.ejb.Remote;

@Remote
public interface StatsStatefulRemote {
	void insertData(Double x);
	void createModel();
	String getStats();
}
