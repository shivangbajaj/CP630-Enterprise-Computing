# EC Hadoop MapReduce Word Count
Author: HBF  
Date: 2022-06-24 (update)  

This project is to get hand on Hadoop MapReduce programming. 

1. Open command console, cd the project root directory. 
2. Run following commands

mvn clean package

This is to create ec-hdmr-wc.jar under target directory.

hadoop jar target/ec-hdmr-wc.jar /ec/wordcount.txt /output

This is to run ec-hdmr-wc.jar on Hadoop. Assume that Hadoop is running, and file wordcount.txt is in HDFS, 


Content of  wordcount.txt is as follows. 

~~~
Hello Hadoop
Hello HDFS
Hello MapReduce
this is a test
~~~

The result of word count is stored in file part-r-00000 on HDFS under /output directory

hadoop fs -ls /output

hadoop fs -cat /output/part-r-00000




