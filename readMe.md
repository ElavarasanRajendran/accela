The Solution was implemented using java, springboot, Spring data JPA.
For the database service I have made use of in memory H2 database.
At the start up of the application, the database will be initialized and required tables will be created.
The Client.java acts as a client class which read the input for performing different operations.
The list of operations implemented are provided below,

1. Add Person
2. Edit Person 
3.Delete Person
4.Add Address to Person
5.Edit Address
6.Delete Address
7.Count number of Persons
8. List All Persons 

This application can be easily plugged on to the web by importing spring starter web and autowiring it with the required controllers

Tools required for building the application:
1. Java 8+
2. Maven for building the applications.

steps for running the application:
1. build the application using mvn clean install.
2. run the class AccelaApplication.class or the jar file generated at  /target/accela-0.0.1-SNAPSHOT.jar 
   by the command java -jar  accela-0.0.1-SNAPSHOT.jar
   
List of third party dependencies used:
1. lombok - for generating boiler plate codes
2. Mokito - for injecting mocks during unit testing.
3. H2 - for in memory database solution.
4. jacoco - for converage report generation. The coverage report can be generated by using the below command
    mvn clean install: prepare-agent test jacoco:report