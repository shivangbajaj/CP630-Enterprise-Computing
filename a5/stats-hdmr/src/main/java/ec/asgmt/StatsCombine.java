package ec.asgmt;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;

public class StatsCombine extends Reducer<Text, StatsWritable, Text, StatsWritable> {
    @Override
    protected void reduce(Text key, Iterable<StatsWritable> values, Context context) throws IOException, InterruptedException {
        StatsWritable combinedStats = combineStats(values);

        context.write(key, combinedStats);
    }

    private StatsWritable combineStats(Iterable<StatsWritable> values) {
        long combinedCount = 0;
        double combinedMin = Double.MAX_VALUE;
        double combinedMax = Double.MIN_VALUE;
        double sum = 0;

        for (StatsWritable val : values) {
            combinedCount += val.getCount();
            combinedMin = Math.min(combinedMin, val.getMin());
            combinedMax = Math.max(combinedMax, val.getMax());
            sum += val.getMean() * val.getCount();
        }

        double combinedMean = sum / combinedCount;
        // double combinedStd = 0;
        double combinedStd = Math.sqrt(sum / combinedCount);

        return new StatsWritable(combinedCount, combinedMin, combinedMax, combinedMean, combinedStd);
    }

}
