# Example Java Projects for Statistics
Author: HBF  
Date: 2022-05-31

Import this Maven poject to Eclipse as existing Maven project, run Maven test, then run each Java program as Java application, i.e. right click a Java program containing the main method, -> Run As -> Java Application. 

 
## Open source math library

We will the open source Apache apache.commons.math3.stat library for this statistic projects. The example programs in this project come from the original [Apatche site](http://commons.apache.org/proper/commons-math/userguide/stat.html).  
Some examples are modified and simplified. 

## Summary statistics

In SummaryStatisticsExample.java, the SummaryStatistics class implement the fundamental interface of StatisticalSummary. 

## Descriptive statistics

In DescriptiveStatistics.java,  the DescriptiveStatistics class also implements the fundamental interface StatisticalSummary plus additional methods. 

## Statistics Utilites

In StatUtilityExample.java, the StatUtils class contain some commonly used static functions.

## Frequency 

In FrequencyExample.java, the Frequency class contains methods for frequency statistics.  

## Simple regression

In SimpleRegressionExample.java, the SimpleRegression class contains methods to calculate simple linear regression model, i.e. y = intercept + slope * x for given set of (x, y) values. 

## Multi-linear regression

In MultiLinearRegressionExample.java, it computes the multi-dimensional linear regression model Y=X*b+u for given training data (X, Y). The OLSMultipleLinearRegression class uses ordinary least squares (OLS) or linear least squares is a method for estimating the unknown parameters in the linear regression model.  The GLSMultipleLinearRegression class uses a generalized least squares method. 

