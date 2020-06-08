FBP-ETL -  ETL (Extract-Transform-Load) framework based on JavaFBP
=======

## Set up test database and table

*From https://www.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html - with thanks!*

**Note: price changed from float in above example to BigDecimal**

Start a MySQL client.  You will also need the MySQL Workbench, and Connector J 8.0 - `mysql-connector-java-8.0.20.jar` .

Run the following SQL statements to create the test database and table.

```
create database if not exists ebookshop;
 
use ebookshop;
 
drop table if exists books;
create table books (
   id int,
   title varchar(50),
   author varchar(50),
   price decimal(15,2),    
   qty int,
   primary key (id));
 
insert into books values (1001, 'Java for dummies', 'Tan Ah Teck', 11.11, 11);
insert into books values (1002, 'More Java for dummies', 'Tan Ah Teck', 22.22, 22);
insert into books values (1003, 'More Java for more dummies', 'Mohammad Ali', 33.33, 33);
insert into books values (1004, 'A Cup of Java', 'Kumar', 44.44, 44);
insert into books values (1005, 'A Teaspoon of Java', 'Kevin Jones', 55.55, 55);
 
select * from books;
```

## First stage in package evolution

![Display MySQL Table](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step05/docs/Step05.png "First stage")

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
