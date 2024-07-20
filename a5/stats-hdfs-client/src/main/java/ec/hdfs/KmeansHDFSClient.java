package ec.hdfs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KmeansHDFSClient {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path resultPath = new Path("C:\\enterprise\\workspace\\a5\\stats-hdfs-client\\src\\main\\java\\ec\\iris.txt");
        List<double[]> centroidList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(resultPath)))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double[] centroid = new double[parts.length];
                
                for (int i = 0; i < parts.length; i++) {
                    centroid[i] = Double.parseDouble(parts[i]);
                }
                
                centroidList.add(centroid);
            }
        }
        
        Centroids centroids = new Centroids(centroidList);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:/enterprise/tmp/model/iris_kmeans.bin"))) {
            oos.writeObject(centroids);
        }
    }
}

