package ec.stats.interfaces;

import javax.ejb.Local;

@Local
public interface StatsStatelessRemote {
	int getCount();
	double getMin();
	double getMax();
	double getMean();
	double getSTD();
	String toString();
}
