package ec.asgmt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class StatsWritable implements Writable {
    private long count;
    private double min;
    private double max;
    private double mean;
    private double std;
    private double sumOfSquaresDiff;
    private double number;

    public StatsWritable() {
        super();
    }

    public StatsWritable(long count, double min, double max, double mean, double std) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.std = std;
    }

    public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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

    public double getSumOfSquaresDiff() {
        return sumOfSquaresDiff;
    }

    public void setSumOfSquaresDiff(double sumOfSquaresDiff) {
        this.sumOfSquaresDiff = sumOfSquaresDiff;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

	public void write(DataOutput out) throws IOException {
        out.writeLong(count);
        out.writeDouble(min);
        out.writeDouble(max);
        out.writeDouble(mean);
        out.writeDouble(std);
        out.writeDouble(sumOfSquaresDiff);
        out.writeDouble(number);
    }

    public void readFields(DataInput in) throws IOException {
        count = in.readLong();
        min = in.readDouble();
        max = in.readDouble();
        mean = in.readDouble();
        std = in.readDouble();
        sumOfSquaresDiff = in.readDouble();
        number = in.readDouble();
    }

    @Override
    public String toString() {
        return count + "," + min + "," + max + "," + mean + "," + std;
    }
}

