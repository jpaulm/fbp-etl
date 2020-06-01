FBP-ETL
=======

#### ETL (Extract-Transform-Load) framework based on JavaFBP

## First stage in package evolution

![Display MySQL Table](https://github.com/jpaulm/fbp-etl/Step05/blob/master/docs/Step05.png "First stage")

This is a running program - at this stage, most of the required info is hard-wired (except, obviously, for the password).

The output is as follows:

~~~~
The records selected are:
Total number of records = 5
Java for dummies, Tan Ah Teck, 11.11, 11
More Java for dummies, Tan Ah Teck, 22.22, 22
More Java for more dummies, Mohammad Ali, 33.33, 33
A Cup of Java, Kumar, 44.44, 44
A Teaspoon of Java, Kevin Jones, 55.55, 55
Run complete.  Time: 0.675 seconds
Counts: C: 6, D: 7, S: 6, R (non-null): 8, DO: 0
~~~~

where the last two lines are standard JavaFBP output.

Thanks to H-C Chua - Nanyang Technological University, Singapore - https://www.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html - for the original (non-FBP) code for this example!
