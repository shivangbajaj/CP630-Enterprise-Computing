package ec.asgmt;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class KmeansMR extends Configured implements Tool {

    public static class KmeansMapper extends Mapper<Object, Text, IntWritable, Text> {
        private List<Double[]> centers;
    
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            
            centers = new ArrayList<Double[]>();
            URI[] cacheFiles = context.getCacheFiles();
            
            if (cacheFiles != null && cacheFiles.length > 0) {
                try {
                    FileSystem fs = FileSystem.get(context.getConfiguration());
                    Path path = new Path(cacheFiles[0].toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(path)));
                    String line;
            
                    while ((line = br.readLine()) != null) {
                        String[] strValues = line.split(",");
                        Double[] center = new Double[strValues.length];
            
                        for (int i = 0; i < strValues.length; i++) {
                            center[i] = Double.parseDouble(strValues[i]);
                        }
            
                        centers.add(center);
                    }
            
                    br.close();
                } catch (IOException e) {
                    System.err.println("Exception reading distributed cache: " + e);
                }
            }
        }

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] stringValues = value.toString().split(",");
            Double[] dataPoint = new Double[stringValues.length];

            for (int i = 0; i < stringValues.length; i++) {
                dataPoint[i] = Double.parseDouble(stringValues[i]);
            }
    
            int nearestCenterIndex = getNearestCenterIndex(dataPoint);
            context.write(new IntWritable(nearestCenterIndex), value);
        }
    
        private int getNearestCenterIndex(Double[] dataPoint) {
            int index = -1;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < centers.size(); i++) {
                double distance = 0.0;

                for (int j = 0; j < dataPoint.length; j++) {
                    distance += Math.pow(dataPoint[j] - centers.get(i)[j], 2);
                }

                distance = Math.sqrt(distance);

                if (distance < minDistance) {
                    minDistance = distance;
                    index = i;
                }
            }

            return index;
        }
    }    

    public static class KmeansReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            double[] sum = new double[0];
            int count = 0;
    
            for (Text value : values) {
                String[] pointStr = value.toString().split(",");
        
                if (sum.length == 0) {
                    sum = new double[pointStr.length];
                }
        
                for (int i = 0; i < pointStr.length; i++) {
                    sum[i] += Double.parseDouble(pointStr[i]);
                }
        
                count++;
            }
    
            if (count > 0) {
                String newCenter = "";
        
                for (int i = 0; i < sum.length; i++) {
                    sum[i] /= count;
                    newCenter += sum[i] + (i < sum.length - 1 ? "," : "");
                }
        
                context.write(key, new Text(newCenter));
            }
        }
    }    

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: KmeansMR <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf(), "Kmeans Clustering");
        
        job.setJarByClass(KmeansMR.class);
        job.setMapperClass(KmeansMapper.class);
        job.setReducerClass(KmeansReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new KmeansMR(), args);
        
        System.exit(res);
    }
}
