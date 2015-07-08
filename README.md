# simple-atm

## Overview
Simple test project, which simulates ATM work.

Uses Spring Boot, with embedded Tomcat. To run, use command
*mvn clean spring-boot:run*

## Full technology stack
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

## What was NOT done (did not have enough time, but was going to do it)
* Server-side validation for some requests
* Good test coverage - now only withdraw() method is covered
* Apache Tiles instead of <jsp:include> on UI
* Improving code quality a little bit more, especially on UI
* Partial masking of Credit Card Number on UI and on logs (****-****-****-5439)
* Actualizing manual dumps/db scripts for creating tables and default data
* Favicon :)

## Screenshots
* http://i.imgur.com/hKipHOD.png
* http://i.imgur.com/m5AfHVt.png
* http://i.imgur.com/b0U62A6.png
* http://i.imgur.com/sSB8pJI.png
* http://i.imgur.com/Z76PoTW.png
* http://i.imgur.com/nK7llDw.png

## Running
* You will need a MySQL database to run this project. The dump for creating DB and user for it is there in main/resources/db/init.sql.
* Tables and test data are created automatically on the first application run (for test data see DefaultTestDataServiceImpl.java).
* Database connection is set up in main/resources/application.properties file.

Have fun
