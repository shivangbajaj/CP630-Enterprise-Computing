package ec.asgmt;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// public class StatsMap extends Mapper<LongWritable, Text, Text, StatsWritable> {
//     private Text statsKey = new Text("stats");

//     @Override
//     protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//         String[] values = value.toString().split(",");
//         double num = Double.parseDouble(values[0]);
//         StatsWritable stats = new StatsWritable(1, num, num, num, 0);

//         context.write(statsKey, stats);
//     }
// }

public class StatsMap extends Mapper<LongWritable, Text, Text, StatsWritable> {
    private Text statsKey = new Text("stats");

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] values = value.toString().split(",");
        double num = Double.parseDouble(values[0]);
        StatsWritable stats = new StatsWritable(1, num, num, num, 0);
        double sumOfSquaresDiff = (num * num) - (num * num / stats.getCount());

        stats.setSumOfSquaresDiff(Math.pow(num - sumOfSquaresDiff, 2));
        context.write(statsKey, stats);
    }
}


