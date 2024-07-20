package ec.stats.sb;

import javax.ejb.Remote;

import ec.stats.StatsSummary;

@Remote
public interface StatsStatelessRemote {
	StatsSummary loadModel();
    int getCount();
    double getMin();
    double getMax();
    double getMean();
    double getSTD();
    String toString();
}
