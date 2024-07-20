EC JPA and JSP
========================

## What's about

This project add additional features to the quickstart greeter project. 

1. Add user password and role to the users table columns.
2. Add userLogin class to control the user login by username and password, which inject UserDao
3. Add model save and check
4. Add navigation of different interfaces using JSF. 


## Testing

1. Testing this project, needs to start MySQL database and Apache web server for database management. 
http://localhost/phpmyadmin/
2. Deploy the project to WildFly 18.x
3. Ppen URL at  http://localhost:8080/ec-jpa-jsf/login.xhtml

Login 

| username |  password |  role |

| :---- -- | :-------  | :----:|

| admin  |   cp630  |   1 |

|  me    |    cp630  |  2 |

|  guest |    guest  |  3 |


4. check the users table in MySQL, and try use their useranem and password to login. 

5. Login as admin, role 1 to do user operations.

6. Login as me, role 2 to do model operations.

7. Login as guest, role 3 to check model operations.

8. Look into the project to see the design and implementation. 



 