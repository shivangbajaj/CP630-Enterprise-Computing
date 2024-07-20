package ec.stats.sb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import ec.stats.sb.interfaces.StatsSingletonLocal;
import ec.stats.sb.interfaces.StatsSingletonRemote;

public class StatsSingleton implements StatsSingletonLocal, StatsSingletonRemote {
	
	private ArrayList<Double> ds = new ArrayList<Double>();
	private double min = Double.MIN_VALUE;
	private double max = Double.MAX_VALUE;
	private double mean = 0;
	private double std = 0;
	private int count = ds.size();

	public void addData(Double x) {
        this.ds.add(x);
        this.count++;
        if (this.count == 1) {
            this.min = x;
            this.max = x;
            this.mean = x;
            this.std = 0;
        } else {
            if (x < this.min) this.min = x;
            if (x > this.max) this.max = x;
            double oldMean = this.mean;
            this.mean = ( this.mean * (this.count - 1) + x)/this.count;
            
            double num1 = std*std * (count - 1);
            double num2 = (x - this.mean)*(x - oldMean);
//            this.std = (count - 1) * this.std * this.std + (x - oldMean)*(x - oldMean);
            this.std = Math.sqrt((num1 + num2)/this.count);
        }
    }

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

    public void stats() {
        this.count = this.ds.size();
        if (this.count == 0) {
            this.min = 0;
            this.max = 0;
            this.mean = 0;
            this.std = 0;
        }
        else {
        	double sum = 0, sumOfSquares = 0;
        	for (Double number : this.ds) {
        		if (number < this.min) this.min = number;
        		if (number > this.max) this.max = number;
        		
        		sum += number;
        		sumOfSquares += number*number;
        	}
        	
        	this.mean = sum/this.count;
    		double variance = (sumOfSquares / this.count) - (this.mean * this.mean);
    		this.std = Math.sqrt(variance);
       }
    }

	public void saveModel() {
		// TODO Auto-generated method stub
		StatsSummary sm = new StatsSummary();
		sm.setCount(getCount());
		sm.setMax(getMax());
		sm.setMean(getMean());
		sm.setMin(getMin());
		sm.setSTD(getStd());
		
		String filePath = "C:/enterprise/tmp/model/stats.bin";
		Path path = Paths.get(filePath);
		
		try {
			if(!Files.exists(path)) {
	        	Files.createDirectories(path);
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
	        oos.writeObject(sm);
	        oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
