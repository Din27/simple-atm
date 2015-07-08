# simple-atm

Simple test project, which simulates ATM work.

Uses Spring Boot, with embedded Tomcat. To run, use command
*mvn clean spring-boot:run*

Full technology stack:
* Java 8
* MySQL
* Spring Boot
* Spring Framework
* Spring MVC
* Spring Security
* Spring Data
* JSP
* Tomcat (Embedded)
* Logback
* Bootstrap 3
* HTML/CSS
* JavaScript/jQuery
* JUnit
* JMock

Some screenshots:
http://i.imgur.com/hKipHOD.png
http://i.imgur.com/m5AfHVt.png
http://i.imgur.com/b0U62A6.png
http://i.imgur.com/sSB8pJI.png
http://i.imgur.com/Z76PoTW.png
http://i.imgur.com/nK7llDw.png

You will need a MySQL database to run this project. The dump for creating DB and user for it is there in main/resources/db/init.sql.
Tables and test data are created automatically on the first application run (for test data see DefaultTestDataServiceImpl.java).
Database connection is set up in main/resources/application.properties file.
