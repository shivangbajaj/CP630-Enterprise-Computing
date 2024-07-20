package ec.stats.sb;

import javax.ejb.Local;

import ec.stats.StatsSummary;

@Local
public interface StatsStatelessLocal {
	StatsSummary loadModel();
    int getCount();
    double getMin();
    double getMax();
    double getMean();
    double getSTD();
    String toString();
}
