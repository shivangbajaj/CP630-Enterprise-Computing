# Parent POM
Author: HBF

## About 

This ec-app project is a Java EE WildFly application master project. It contains three dependent sub-projects ec-ejb, ec-ejb-web, and ec-ear. The ec-ear wraps ec-ejb and ec-ejb-web packages for deployment. 


## How to use

Packaging and install: Run the following Maven command to package, it will create It creates ear deployment package ec-ear.ear under ec-ear/target. The install goal does the packaging and then install packages to local repo, so that to make ec-ejb and ec-web-web available for dependency. 

~~~
mvn clean install
~~~

Deployment: assuming WildFly is running on local machine. 

~~~
mvn wildfly:deploy
~~~ 


Check URL [http://127.0.0.1:8080/ec-ejb-web/](http://127.0.0.1:8080/ec-ejb-web/) to see the result of deployment. 

Undeployment: 

~~~
mvn wildfly:undeploy
~~~ 

