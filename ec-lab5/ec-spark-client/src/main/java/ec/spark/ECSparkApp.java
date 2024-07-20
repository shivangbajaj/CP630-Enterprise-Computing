package ec.spark;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

public class ECSparkApp {
	
  public static void main(String[] args) {

    String datafile = "wordcount.txt";
    SparkConf conf = new SparkConf().setAppName("Simple Application");
	
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> logData = sc.textFile(datafile).cache();

    long numSs = logData.filter(new Function<String, Boolean>() {
		private static final long serialVersionUID = 1L;
	public Boolean call(String s) {
		  System.out.println(s); 
		  return s.contains("Spark"); }
    }).count();
    

    long numHs = logData.filter(new Function<String, Boolean>() {
		private static final long serialVersionUID = 1L;
	public Boolean call(String s) { 
	  return s.contains("Hello"); }
    }).count();
	
    System.out.println("Number of lines with Spark : " + numSs);    
	System.out.println("Number of lines with Hello : " + numHs); 
	
	sc.close();
	sc.stop();
  }
}