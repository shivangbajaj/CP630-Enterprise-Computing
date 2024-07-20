package ec.stats.sb.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.Local;

import ec.stats.StatsSummary;

public interface StatsSingletonLocal {
	void addData(Double x);
	int getCount();
	void stats();
	void saveModel();
}
