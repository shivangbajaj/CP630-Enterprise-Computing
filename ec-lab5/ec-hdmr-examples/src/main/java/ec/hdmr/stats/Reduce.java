package ec.hdmr.stats;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Average, Text, DoubleWritable> {
	DoubleWritable avg = new DoubleWritable();

	protected void reduce(Text key, Iterable<Average> values, Context context) throws IOException, InterruptedException {
		double total = 0;
		long count = 0;
		double avgg = 0;

		for (Average val : values) {
			total += val.number *  val.count;
			count += val.count;
			avgg = total / count;
		}
		
		System.out.println("avg==========================="+avg);
		System.out.println("count==========================="+count);
		
		avg.set(avgg);
		context.write(key, avg);
	}
}