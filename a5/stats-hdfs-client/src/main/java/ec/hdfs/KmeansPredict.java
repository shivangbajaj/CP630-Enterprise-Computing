package ec.hdfs;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;

public class KmeansPredict {
    public static void main(String[] args) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:/enterprise/tmp/model/iris_kmeans.bin"))) {
            Centroids centroids = (Centroids) ois.readObject();
            double[] instance = parseInstance(args[0]);
            int nearestCentroidIndex = predictNearestCentroid(centroids.getCentroids(), instance);

            System.out.println("Nearest centroid index: " + nearestCentroidIndex);
        }
    }

    private static double[] parseInstance(String instanceStr) {
        String[] parts = instanceStr.split(",");
        double[] instance = new double[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            instance[i] = Double.parseDouble(parts[i]);
        }
        
        return instance;
    }

    private static int predictNearestCentroid(List<double[]> centroids, double[] instance) {
        int nearestIndex = -1;
        double minDistance = Double.MAX_VALUE;
        
        for (int i = 0; i < centroids.size(); i++) {
            double distance = 0.0;
        
            for (int j = 0; j < instance.length; j++) {
                distance += Math.pow(instance[j] - centroids.get(i)[j], 2);
            }
        
            distance = Math.sqrt(distance);
        
            if (distance < minDistance) {
                minDistance = distance;
                nearestIndex = i;
            }
        }
        
        return nearestIndex;
    }
}
