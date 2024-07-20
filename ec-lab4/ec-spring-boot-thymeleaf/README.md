# Spring Boost MVC
Author: HBF  
Date: 2022-06-22

## About this project

This project is to get hand on Spring Boot for stand alone web server with Java [Themeleaf template technology](https://www.thymeleaf.org/)

## Run within Eclipse

1. Run App.java as Java application, it starts a web server. 
2. Open URLs

- http://localhost:8080/greeting
- http://localhost:8080/greeting?name=CE
- http://localhost:8080/json/ec
- http://localhost:8080/xml/ec

3. Stop the program

## Standalone program

1. Open cmd console, cd to the folder of ec-spring-boot-thymeleaf.
2. Run Maven command: mvn clean package
3. Run command: java -jar target/ec-spring-boot-thymeleaf.jar to start a web server.
4. Open URLs

- http://localhost:8080/greeting
- http://localhost:8080/greeting?name=CE
- http://localhost:8080/json/ec
- http://localhost:8080/xml/ec

5. Stop the program

## Read files 

-  src/main/resources/static directory contains static html files 
-  src/main/resources/template directory contains thymeleave template html files 


