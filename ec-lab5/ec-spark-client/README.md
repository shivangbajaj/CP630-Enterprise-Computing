# EC Spark Client
Author: HBF  
Date: 2022-06-24

This project is to get hand on Spark Java client programming. 


1. Run the following Maven command to create target/ec-spark-client.jar

~~~
mvn clean package 
~~~

2. Open a cmd console, cd the root directory of the 

~~~
spark-submit --class "ECSparkApp" --master spark://10.0.75.1:7077 target/ec-spark.jar
~~~

Here spark://10.0.75.1:7077 is the Spark master address.
