FBP-ETL
=======

#### Adding Customized Types to ReadJDBC.java 

So far, we have been using standard data types (SQL and Java).  However, as we have said in a number of publications, "big decimal" or, still worse, "float" are not adequate for monetay amounts.  We are therefore planning to convert the `Book` layout to use `MPrice` from https://github.com/jpaulm/jbdtypes .  We will also be adding a type `PRICE` to the database column definitions.
