package ec.hdfs;

import java.io.Serializable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class StatsSummary implements Writable, Serializable {
    private long count;
    private double min;
    private double max;
    private double mean;
    private double stdDev;

    // Default constructor for serialization
    public StatsSummary() {
    }

    // Constructor with all fields
    public StatsSummary(long count, double min, double max, double mean, double stdDev) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.stdDev = stdDev;
    }

    // Getters and setters for all fields
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

    public double getStdDev() {
        return stdDev;
    }

    public void setStdDev(double stdDev) {
        this.stdDev = stdDev;
    }

    // Implementation of the Writable interface methods
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(count);
        out.writeDouble(min);
        out.writeDouble(max);
        out.writeDouble(mean);
        out.writeDouble(stdDev);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        count = in.readLong();
        min = in.readDouble();
        max = in.readDouble();
        mean = in.readDouble();
        stdDev = in.readDouble();
    }

    // toString method for easy printing of the contents
    @Override
    public String toString() {
        return "StatsSummary{" +
                "count=" + count +
                ", min=" + min +
                ", max=" + max +
                ", mean=" + mean +
                ", stdDev=" + stdDev +
                '}';
    }
}
