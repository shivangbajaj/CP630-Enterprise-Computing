# ECStatistics Project

This project implements a simple statistics class, `ECStatistics`, that calculates descriptive statistics incrementally using an ArrayList. The statistics include count, minimum, maximum, mean, and standard deviation.

# Running JUnit Tests
1. Clone or download the project from the repository.
2. Open a terminal or command prompt and navigate to the project directory.
3. Run the following Maven command to execute JUnit tests: mvn test

#How to Build:

1.Open a terminal or command prompt and navigate to the project directory.
2.Run the following Maven command to build the project: mvn clean install

#Execution

1.After building the project, you will find the executable JAR file (ec-stat.jar) in the target directory.
2.Run the JAR file using the following command: java -jar target/ec-stat.jar

This will execute the MyStatistics class, adding data values from 1 to 10000 and printing the calculated statistics.

The project includes logging statements using the java.util.logging package. Log messages are displayed on the console, providing information about method calls and statistics calculations.




