//Linear Regression
import org.apache.spark.mllib.linalg.Vectors

val housingLines = sc.textFile("data/housing.data", 6)
val housingVals = housingLines.map(x => Vectors.dense(x.split(",").map(_.trim().toDouble)))

import org.apache.spark.mllib.linalg.distributed.RowMatrix
val housingMat = new RowMatrix(housingVals)
val housingStats = housingMat.computeColumnSummaryStatistics()

import org.apache.spark.mllib.stat.Statistics
val housingStats = Statistics.colStats(housingVals)

housingStats.min
housingStats.max
housingStats.mean

val housingColSims = housingMat.columnSimilarities()
val housingCovar = housingMat.computeCovariance()

import org.apache.spark.mllib.regression.LabeledPoint
val housingData = housingVals.map(x => {
val a = x.toArray
LabeledPoint(a(a.length-1), Vectors.dense(a.slice(0, a.length-1)))
})


//Splitting the data  into training and validation sets.
val sets = housingData.randomSplit(Array(0.8, 0.2))
val housingTrain = sets(0)
val housingValid = sets(1)

//Feature scaling and mean normalization
import org.apache.spark.mllib.feature.StandardScaler
val scaler = new StandardScaler(true, true).fit(housingTrain.map(x => x.features))
val trainScaled = housingTrain.map(x => LabeledPoint(x.label, scaler.transform(x.features)))
val validScaled = housingValid.map(x => LabeledPoint(x.label, scaler.transform(x.features)))


//Linear regression
import org.apache.spark.mllib.regression.LinearRegressionWithSGD

val alg = new LinearRegressionWithSGD()
alg.setIntercept(true)
alg.optimizer.setNumIterations(200)
trainScaled.cache()
validScaled.cache()
val model = alg.run(trainScaled)

//Predicting the target values
val validPredicts = validScaled.map(x => (model.predict(x.features),x.label))
validPredicts.collect()

//Evaluating the model’s performance
math.sqrt(validPredicts.map{case(p,l) => math.pow(p-l,2)}.mean())

import org.apache.spark.mllib.evaluation.RegressionMetrics
val validMetrics = new RegressionMetrics(validPredicts)
validMetrics.rootMeanSquaredError
validMetrics.meanSquaredError

//interpretion of model
println(model.weights.toArray.map(x => x.abs).zipWithIndex.sortBy(_._1).mkString(", "))

//Loading and saving the model
model.save(sc, "data/model")
import org.apache.spark.mllib.regression.LinearRegressionModel
val model = LinearRegressionModel.load(sc, "data/model")
