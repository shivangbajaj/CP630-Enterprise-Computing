# Parent POM
Author: HBF

## About 

This ec-ejb-web project is JavaEE project containing web components. 


## How to use

Packaging and install: Run the following Maven command to package, it will create It creates jar deployment package ec-ejb-web.war under target. The install goal does the packaging and then install packages to local repo, so that to make ec-ejb-web available for dependency. 

~~~
mvn clean install 
~~~

Deployment: this ec-ejb-web package can not be deploy by itself as it dependency on ec-ejb. 
