package ec.stats.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.Local;

import ec.stats.StatsSummary;

@Local
public interface StatsStatelessLocal {
	int getCount();
	double getMin();
	double getMax();
	double getMean();
	double getSTD();
	String toString();
	public StatsSummary loadModel() throws FileNotFoundException, IOException, ClassNotFoundException;
}
