//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.sb;
import java.applet.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import ec.stats.DescriptiveStatistics;
import ec.stats.StatsSummary;

@Singleton
@LocalBean
public class StatsSingleton implements StatsSingletonLocal, StatsSingletonRemote
{
	
	////////
	public static final String MODEL_SAVE_PATH = "C:/enterprise/tmp/model/stats.bin";
	public static final Logger LOGGER = Logger.getLogger(StatsSingleton.class.getName());
	
	ArrayList<Double> ds = new ArrayList<Double>();
	private Double min;
	private Double max;
	private Double mean;
	private Double variance;
	private Double std;
	
	public StatsSingleton(){}

	@Override
	public int getCount(){return ds.size();}

	@Override
	public DescriptiveStatistics stats()
	{
		if(ds.size() == 0)
		{
			max = min = mean = std = variance = 0d;
			return new DescriptiveStatistics();
		}
		int n = ds.size();
		// compute min, max & mean
		max = min = ds.get(0);
		mean = 0d;
		for(double x : ds)
		{
			if(max < x) max = x;
			if(min > x) min = x;
			mean += x; // sum of all elements;
		}
		mean = mean / (double)n;
		
		// std calculation
		variance = 0d;
		for(double x : ds)
		{
			variance = variance + (x - mean)*(x - mean);
		}
		std = Math.sqrt(variance/n);

		DescriptiveStatistics descStats = new DescriptiveStatistics(n, min, max, mean, variance, std);

		min = max = mean = std = variance = 0.0;
		ds.clear();
		
		return descStats;
	}

	@Override
	public void addData(Double x)
	{
		ds.add(x);
		int n = ds.size();
		if(ds.size() == 1)
		{
			mean = x;
			min = x;
			max = x;
			variance = 0d;
			std = 0d;
		} else {
			min = Math.min(x, min);
			max = Math.max(x, max);
			
			// incremental mean
			double prev_mean = mean;
			mean = (mean * (n-1) + x)/n;
			// incremental std
			double prev_var = variance;
			variance = prev_var + (x - prev_mean)*(x - mean);
			std = Math.sqrt(variance/n);
		}
	}

	@Override
	public void saveModel()
	{
		StatsSummary statsSummary = new StatsSummary();
		statsSummary.setCount(ds.size());
		statsSummary.setMin(min);
		statsSummary.setMax(max);
		statsSummary.setMean(mean);
		statsSummary.setSTD(std);

		try {
			FileOutputStream fileOut = new FileOutputStream(MODEL_SAVE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(statsSummary);
			out.close();
			fileOut.close();
			LOGGER.info("model=" + statsSummary.toString());
		}
		catch (IOException i)
		{
			i.printStackTrace();
		}
	}
}