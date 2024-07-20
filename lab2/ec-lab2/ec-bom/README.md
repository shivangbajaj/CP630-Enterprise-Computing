# Parent POM
Author: HBF

## About 

This ec-bom project contains the pom.xml, a BOM (Bill of Material) file for a list of common properties, and version management elements to be used by a group of Maven projects. 

## How to install

Run the following Maven command to add/install the parent POM to local repo. 

~~~
mvn install
~~~ 


Then add the following element to the pom.xml of a project that uses it. 

~~~
	<parent>
		<groupId>ec.lab</groupId>
		<artifactId>ec-pom</artifactId>
		<version>0.1.0</version>
	</parent>
~~~	


Or use the following with relative path to this pom.xml if it is not installed in repo. 


~~~
	<parent>
		<groupId>ec.lab</groupId>
		<artifactId>ec-pom</artifactId>
		<version>0.1.0</version>
		<relativePath> ?????/pom.xml</relativePath>
	</parent>
~~~	
