package ec.hdmr.stats;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Combine extends Reducer<Text, Average, Text, Average> {

	protected void reduce(Text key, Iterable<Average> values, Context context) throws IOException, InterruptedException {
		double total = 0;
		long count = 0;
		double avg = 0;

		for (Average val : values) {
			total += val.number;
			count += val.count;
			avg = total / count;
		}
		
		System.out.println("avg==========================="+avg);
		System.out.println("count==========================="+count);
		
		context.write(key, new Average(avg, count));
	}
}