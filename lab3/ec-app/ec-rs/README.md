# Parent POM
Author: HBF

## About 

This ec-rs project is JavaEE project containing RESTful WS components. 


## How to use

Packaging and install: Run the following Maven command to package, it will create It creates jar deployment package ec-rs.war under target. The install goal does the packaging and then install packages to local repo, so that to make ec-rs available for dependency. 

~~~
mvn clean install 
~~~

Deployment: this ec-rs package can not be deploy by itself as it dependency on ec-ejb.  
