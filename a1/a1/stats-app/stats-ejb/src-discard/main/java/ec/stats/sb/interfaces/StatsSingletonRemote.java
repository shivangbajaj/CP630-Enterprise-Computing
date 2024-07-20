package ec.stats.interfaces;

import javax.ejb.Remote;

@Remote
public interface StatsSingletonRemote {
	void addData(Double x);
	int getCount();
	void stats();
	void saveModel();
}
