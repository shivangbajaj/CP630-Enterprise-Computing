package ec.hdmr.stats;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRMean {

public static void main(String[] args) throws Exception { 

    Configuration conf = new Configuration();
    if (args.length != 2) {
        System.err.println("Usage: SecondarySort <in> <out>");
        System.exit(2);
    }
    //Job job = new Job(conf, "CustomCobiner");
    Job job = Job.getInstance(conf, "HDMR mean");
    job.setJarByClass(MRMean.class);
    job.setMapperClass(Map.class);
    job.setCombinerClass(Combine.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Average.class);
    job.setReducerClass(Reduce.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);     
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}