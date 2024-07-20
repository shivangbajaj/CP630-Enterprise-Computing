package ec.stats.interfaces;

import javax.ejb.Local;

@Local
public interface StatsStatefulLocal {
	void insertData(Double x);
	void createModel();
	String getStats();
}
