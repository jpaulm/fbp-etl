FBP-ETL
=======

#### Adding Customized Types to ReadJDBC.java 

So far, we have been using standard data types (SQL and Java).  However, as we have said in a number of publications, "BigDecimal" or, still worse, "float", are not adequate for monetary amounts.  We are therefore planning to convert the `Book` layout to use `MPrice` from https://github.com/jpaulm/jbdtypes - which see.  We intended to add a type `PRICE` to the database column definitions, but the MySQL platform does not allow this, so we will have to use `VARCHAR`.  

`Book.java` must be compiled separately from `ReadJDBC`, but the latter will eventually be moved into the JavaFBP libraries, so this will be in one of the following steps.


**Under construction**
