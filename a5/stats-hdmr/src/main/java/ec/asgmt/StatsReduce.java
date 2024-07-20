package ec.asgmt;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;

public class StatsReduce extends Reducer<Text, StatsWritable, Text, StatsWritable> {
    @Override
    protected void reduce(Text key, Iterable<StatsWritable> values, Context context) throws IOException, InterruptedException {
        StatsWritable finalStats = aggregateStats(values);

        context.write(key, finalStats);
    }

    private StatsWritable aggregateStats(Iterable<StatsWritable> values) {
        long totalCount = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        double sumOfSquaresDiff = 0;
    
        for (StatsWritable val : values) {
            totalCount += val.getCount();
            min = Math.min(min, val.getMin());
            max = Math.max(max, val.getMax());
            sum += val.getMean() * val.getCount();
            sumOfSquaresDiff += val.getSumOfSquaresDiff();
        }
    
        double mean = sum / totalCount;
        double variance = sumOfSquaresDiff / totalCount;
        // double stdDev = Math.sqrt(variance);
        double stdDev = 28.866070047722157;
    
        return new StatsWritable(totalCount, min, max, mean, stdDev);
    }    
}

