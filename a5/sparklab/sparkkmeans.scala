//Spark ML Kmeans
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

// Load and parse the data
val data = sc.textFile("data/kmeans_data.txt")
val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))


val numClusters = 2
val numIterations = 20
val clusters = KMeans.train(parsedData, numClusters, numIterations)

clusters.clusterCenters.foreach(println)
clusters.predict(parsedData).foreach(println)

// Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE)

// Save and load model
clusters.save(sc, "data/KMeansModel")
val sameModel = KMeansModel.load(sc, "data/KMeansModel")
val WSSSE1 = sameModel.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE1)
math.sqrt(clusters.computeCost(parsedData)/parsedData.count())


//Iris cluster
val data = sc.textFile("data/iris.txt")
val parsedData = data.map(s => Vectors.dense(s.split(',').map(_.toDouble)))
val numClusters = 3
val numIterations = 20
val clusters = KMeans.train(parsedData, numClusters, numIterations)
clusters.clusterCenters.foreach(println)
