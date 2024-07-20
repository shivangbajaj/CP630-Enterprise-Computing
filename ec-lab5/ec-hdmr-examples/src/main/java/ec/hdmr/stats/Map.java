package ec.hdmr.stats;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<Object, Text, Text, Average> {
	private Text word = new Text("1");
	Text name = new Text();
	String[] row;

	protected void map(Object offSet, Text line, Context context) throws IOException, InterruptedException {
		row = line.toString().split(" ");
		//System.out.println("Key " + row[0] + "Value " + row[1]);
		System.out.println(row[0]);
		context.write(word, new Average(Double.parseDouble(row[0]), (long) 1.0));
	}
}

