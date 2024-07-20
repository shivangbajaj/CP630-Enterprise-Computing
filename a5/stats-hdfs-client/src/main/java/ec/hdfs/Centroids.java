package ec.hdfs;

import java.io.Serializable;
import java.util.List;

public class Centroids implements Serializable {
    private List<double[]> centroids;

    public Centroids(List<double[]> centroids) {
        this.centroids = centroids;
    }

    public List<double[]> getCentroids() {
        return centroids;
    }

    public void setCentroids(List<double[]> centroids) {
        this.centroids = centroids;
    }
}
