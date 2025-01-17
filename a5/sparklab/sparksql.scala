//Spark SQL  -- DataFrame

import org.apache.spark.sql.{Dataset, DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.sql._

//load from JSon file
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val input = sqlContext.read.json("data/cars.json")
input.registerTempTable("Cars1")
val result = sqlContext.sql("SELECT * FROM Cars1")
result.show()
result.show(2)

//load data from file, parse and create convert to DF
case class Fruits(id: Int, name: String, quantity: Int)
val fruits = sc.textFile("data/fruits.txt").map(_.split(",")).map(frt => Fruits(frt(0).trim.toInt, frt(1), frt(2).trim.toInt)).toDF()
fruits.show() 
fruits.printSchema
fruits.registerTempTable("fruits")

//creare DF by query
val records = sql("SELECT * FROM fruits")
records.show()

val resultDf = sql("select * from fruits")
resultDf.show()

val resultDf1 = fruits.where("id > 4")
resultDf1.show()

//fruits.write.saveAsTable("fruits1")
fruits.write.mode("overwrite").saveAsTable("fruits1")
val resultDf = sql("select * from fruits1")
resultDf.show()

//convert DF to RDD
val fruitsRdd = fruits.rdd
fruitsRdd.collect()

