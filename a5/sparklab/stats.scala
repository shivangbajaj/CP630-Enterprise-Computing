// simple summary statistics
// https://spark.apache.org/docs/latest/mllib-statistics.html

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}
import java.io.File 
import java.io.PrintWriter 

val dataLines = sc.textFile("data/statsdata.txt", 1)
val dataVals = dataLines.map(x => Vectors.dense(x.split(",").map(_.trim().toDouble)))
//val dataMat = new RowMatrix(dataVals)
//val dataStats = dataMat.computeColumnSummaryStatistics()
val dataStats = Statistics.colStats(dataVals)

// get 
var count = dataStats.count
var min = dataStats.min(0)
var max = dataStats.max(0)
var mean = dataStats.mean(0)
var variance = dataStats.variance(0)
var std = Math.sqrt(variance)

// save to file
val file_Object = new File("data/skstats.txt" )      
val print_Writer = new PrintWriter(file_Object)          
print_Writer.write(count+","+min+","+max+","+mean+","+std)  
print_Writer.close()         

