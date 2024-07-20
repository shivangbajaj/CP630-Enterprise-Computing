# Spring Boost MVC JSP for WildFly
Author: HBF  
Date: 20222-06-22

## About this project

This project ec-spring-boot-jsp-wildfly project is converted from a ec-spring-boot-jsp project. 

## Run within Eclipse

1. Run Maven goals "clean package" to build the project. It creates target/ec-spring-boot-jsp-wildfly.war
2. Run Maven goal "wildfly:deploy" to deploy it on WildFly, or by console command "mvn wildfly:deploy"
3. Open URLs

- [http://localhost:8080/ec-spring-boot-jsp-wildfly/json/ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/json/ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/xml/ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/xml/ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring?name=EC](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring?name=EC)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2?name=EC](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2?name=EC)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/post-form](http://localhost:8080/ec-spring-boot-jsp-wildfly/post-form)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly//login](http://localhost:8080/ec-spring-boot-jsp-wildfly/login)   username:admin password:cp630

4. Undeploy it by Maven goal "wildfly:undeploy".  

