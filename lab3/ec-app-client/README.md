# Parent POM
Author: HBF

## About 

This ec-app-client project is a standalone Java project. It access EJB components deployed on WildFly server. 


## How to use

Packaging: Run the following Maven command to package, it will create ec-app-client.jar under the target folder.   

~~~
mvn clean package
~~~

Run:  Assume ec-ejb components are deployed on WildFly server. 

~~~
java -jar target/ec-app-client.jar
~~~ 
