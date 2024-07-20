package ec.hdfs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StatsHDFSClient {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path inFile = new Path("stats-hdfs-client\\src\\main\\java\\ec\\statsdata.txt");
        StatsSummary statsSummary = new StatsSummary();
        
        try (FSDataInputStream in = fs.open(inFile)) {
            statsSummary.readFields(in);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/enterprise/tmp/model/hdstats.bin"))) {
            oos.writeObject(statsSummary);
        }
    }
}
