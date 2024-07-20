# Parent POM
Author: HBF

## About 

This ec-ejb project is JavaEE project containing EJB components. 


## How to use

Packaging and install: Run the following Maven command to package, it will create It creates jar deployment package ec-ejb.jar under target. The install goal does the packaging and then install packages to local repo, so that to make ec-ejb available for dependency. 

~~~
mvn clean install 
~~~

Deployment: assuming WildFly is running on local machine. 

~~~
mvn wildfly:deploy
~~~ 


It needs to EJB client to access the EJB components on WilfFly server. 


Undeployment: 

~~~
mvn wildfly:undeploy
~~~ 

