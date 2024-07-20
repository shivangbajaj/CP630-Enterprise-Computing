//Spark RDD examples
//https://rklicksolutions.wordpress.com/category/spark-1-6/

val flatMapLines = sc.parallelize(List("hello Spark", "hi Spark"))
val flatMapResult =  flatMapLines.flatMap(line => line.split(" "))
println("flatMap:" + flatMapResult.first())

val inputData = sc.parallelize(1 to 9, 3)
val inputData1 = sc.parallelize(1 to 9)
val mapPartitionResult = inputData.mapPartitions(x => List(x.next).iterator)
println("mapPartition is :" + mapPartitionResult.collect().mkString(","))

//union
val inputRdd1 = sc.parallelize(List(1, 2, 3))
val inputRdd2 = sc.parallelize(List(3, 5, 7))
val resultInputUnion = inputRdd1.union(inputRdd2)
println("Union:" + resultInputUnion.collect().mkString(","))

//intersection
val inputRdd1 = sc.parallelize(List(1, 2, 3))
val inputRdd2 = sc.parallelize(List(3, 5, 7))
val resultIntersection = inputRdd1.intersection(inputRdd2)
println("Intersection:" + resultIntersection.collect().mkString(","))

//join
val inputTupple1 = sc.parallelize(List((1, 2), (3, 4), (4, 7)))
val inputTupple2 = sc.parallelize(List((5, 9), (1, 6)))
val resultJoin = inputTupple1.join(inputTupple2)
println("inner join between two RDDs." + resultJoin.collect().mkString(","))

//reduce reduce(func) Combine the elements of the RDD together in parallel.
val input = sc.parallelize(List(3, 2, 4, 6))
val inputs= sc.parallelize(List(2, 4, 2, 3))
val rUnion = input.union(inputs)
val resultReduce = rUnion.reduce((x, y) => x + y)
println("reduce:" + resultReduce + " ")

//collect() Return all elements from the RDD.
val inputElement = sc.parallelize(List(2, 3, 4, 4))
println("collect" + inputElement.collect().mkString(","))

//count() returns a count of the elements the RDD.
val inputCount = sc.parallelize(List(2, 3, 4, 4))
println("count:" + inputCount.count())

//first() Returns the first element of the dataset (similar to take (1)).
val inputFirst =sc.parallelize(List(2, 3, 4, 4))
println("fist:"+ inputFirst.first())

//take(num) Return num elements from the RDD.
val inputTake = sc.parallelize(List(2, 3, 4, 4))
println("take :" + inputTake.take(2).mkString(","))


//foreach() -- Runs a function func on each element of the dataset.
val inputForeach = sc.parallelize(List(2, 3, 4, 4))
inputForeach.foreach(x => println(x + 1))

//countByKey() -- Count the number of elements for each key.
val inputAction = sc.parallelize(List((1, 2), (2, 3), (5, 4)))
println("countByKey :" + inputAction.countByKey().mkString(","))


//saveAsTextFile() -- Write the elements of the dataset in a given directory in the local filesystem.
val inputFile = sc.textFile("data/wordcount.txt") 
val count = inputFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_+_)
count.saveAsTextFile("output")
// check part-0000 and part-0001 in the ouptput directory



