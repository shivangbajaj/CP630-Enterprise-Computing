# Spring Boost Standalone application 
Author: HBF  
Date: 2022-06-22

## About this project

This project is to get hand on building standalone Java programs using Spring Boot. 

## Run within Eclipse

Run App.java as Java application

## Standalone program

1. Open cmd console, cd to the root directory of ec-spring-boot-mvn project. 
2. Run commands

~~~
mvn clean package
java -jar target/ec-spring-boot-mvn.jar 90
~~~

We see that there is not configuration xml files in this boot project. Boot framework handles all DI matters. 

