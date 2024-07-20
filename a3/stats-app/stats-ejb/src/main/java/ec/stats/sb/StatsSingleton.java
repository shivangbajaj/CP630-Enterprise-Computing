package ec.stats.sb;

import javax.ejb.Singleton;

import ec.stats.StatsSummary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class StatsSingleton implements StatsSingletonLocal, StatsSingletonRemote {
	public static final String SAVE_PATH = "C:\\enterprise\\tmp\\model\\stats.bin";
	public static final Logger LOGGER = Logger.getLogger(StatsSingleton.class.getName());
	
	ArrayList<Double> ds = new ArrayList<Double>();
	private double min;
	private double max;
	private double mean;
	private double std;

    @Override
    public void addData(Double x) {
    	ds.add(x);
    	int count = ds.size();
		if (count == 1) {
			min = x;
			max = x;
			mean = x;
			std = 0;
		} else {
			if (x < min)
				min = x;
			if (x > max)
				max = x;

			double delta = x - mean;
			mean += delta / count;

			double deltaSquared = x - mean;
			std = Math.sqrt((count - 1) * std * std / count + deltaSquared * delta / count);
		}
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public void stats() {
    	int count = ds.size();
		if (count == 0) {
			min = 0;
			max = 0;
			mean = 0;
			std = 0;
		} else {
			double sum = 0;
			double sumSquaredDiff = 0;

			min = ds.get(0);
			max = ds.get(0);

			for (Double x : ds) {
				if (x < min)
					min = x;
				if (x > max)
					max = x;

				// Update sum for mean calculation
				sum += x;

				// Update sumSquaredDiff for standard deviation calculation
				sumSquaredDiff += x * x;
			}

			// Calculate mean
			mean = sum / count;

			// Calculate standard deviation
			double meanSquared = mean * mean;
			std = Math.sqrt((sumSquaredDiff - 2 * mean * sum + count * meanSquared) / count);
		}
    }

    @Override
    public void saveModel() {
    	StatsSummary statsSummary = new StatsSummary();
		statsSummary.setCount(ds.size());
		statsSummary.setMin(min);
		statsSummary.setMax(max);
		statsSummary.setMean(mean);
		statsSummary.setSTD(std);

		try {
			FileOutputStream fos = new FileOutputStream(SAVE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(statsSummary);
			out.close();
			fos.close();
			LOGGER.info("Model Saved: " + statsSummary.toString());
		}
		catch (IOException i)
		{
			i.printStackTrace();
		}
    }
}
