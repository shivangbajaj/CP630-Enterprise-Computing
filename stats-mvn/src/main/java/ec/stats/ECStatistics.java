package ec.stats;
import java.util.ArrayList;

public class ECStatistics implements Statistics {

    private ArrayList<Double> ds;
    private int count;
    private double min;
    private double max;
    private double sum;
    private double mean;
    private double std;

    public ECStatistics() {
        this.ds = new ArrayList<Double>();
        this.count = 0;
        this.min = Double.POSITIVE_INFINITY;
        this.max = Double.NEGATIVE_INFINITY;
        this.sum = 0.0;
        this.std = 0.0;
    }

    public void addData(double x) {
        ds.add(x);
        count += 1;
        if (count == 1) {
            min = x;
            max = x;
            mean = x;
            std = 0;
        } else {
            if (x < min) min = x; // this uses the existing min to update the new min. 
            if (x > max) max = x;    
            
            double delta = x - mean;
            mean += delta / count;

            double deltaSquared = x - mean;
            std = Math.sqrt((count - 1) * std * std / count + deltaSquared * delta / count);
        }
    }

    public void stats() {
        count = ds.size();
        if (count == 0) {
            min = 0;
            max = 0;
            mean = 0;
            std = 0;
        } else {
            double sum = 0.0;
            double sumSquaredDifferences = 0.0;
            double minValue = Double.POSITIVE_INFINITY;
            double maxValue = Double.NEGATIVE_INFINITY;

            for (Double x : ds) {
                // Update sum and sumSquaredDifferences incrementally
                sum += x;
                sumSquaredDifferences += x * x;

                // Update min and max
                minValue = Math.min(minValue, x);
                maxValue = Math.max(maxValue, x);
            }

            // Calculate mean
            mean = sum / count;

            // Calculate standard deviation
            double meanSquared = mean * mean;
            std = Math.sqrt((sumSquaredDifferences - 2 * mean * sum + count * meanSquared) / count);

            // Update min and max
            min = minValue;
            max = maxValue;
        }
    }


    public int getCount() {
        return count;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getMean() {
        return mean;
    }

    public double getSTD() {
        return std;
    }
}
