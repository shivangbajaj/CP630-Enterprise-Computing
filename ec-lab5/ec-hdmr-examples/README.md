Hadoop MapReduce examples
=========================

1. WordCount1
 
2. WorrCount2 

https://hadoop.apache.org/docs/r2.7.1/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html

file01
Hello World, Bye World!

file02
Hello Hadoop, Goodbye to hadoop.

patterns.txt
\.
\,
\!
to

jar ec-hdmr.jar com.ec.lab.WordCount2 -Dwordcount.case.sensitive=true  /wc /output -skip /patterns.txt

Bye     1
Goodbye 1
Hadoop  1
Hello   2
World   2
hadoop  1


3. SalesCountry from 
https://www.guru99.com/create-your-first-hadoop-program.html

copy directory ./inputMapReduce to the same directory on HDFS

hadoop fs -copyFromLocal ./inputMapReduce /

hadoop jar ec-hdmr.jar SalesCountry.SalesCountryDriver /inputMapReduce /mapreduce_output_sales

4. Compute mean

User defined Writable object Average, combiner, calculate partial average by map and combiner
Avoid over-flow. 

hadoop jar -rm -r  /output
hadoop jar ec-hdmr.jar com.ec.mean.MRMean /mean/data.txt /output


How to calculate standard deviation. 
tao^2 = sum(X^2)/N  - sum(X)/N

