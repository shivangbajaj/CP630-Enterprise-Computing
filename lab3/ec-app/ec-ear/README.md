# Parent POM

Author: HBF

## About 

This ec-ear project is to wrap ec-ejb and ec-ejb-web packages into a ear package for deployment. 

## How to use

Packaging:  Assuming both ec-ejb and ec-ejb-web are avaiable. 

~~~
mvn clean package
~~~

Deployment: assuming WildFly is running on local machine. 

~~~
mvn wildfly:deploy
~~~ 

Testing:

- Check URL [http://127.0.0.1:8080/ec-ejb-web/](http://127.0.0.1:8080/ec-ejb-web/) to see the result of deployment. 
- The session bean components on WildFly server can also be accessed by EJB client. 


Undeployment: 

~~~
mvn wildfly:undeploy
~~~ 

