FBP-ETL
=======

#### Adding Customized Types to ReadJDBC.java 

So far, we have been using standard data types (SQL and Java).  However, as we have said in a number of publications, "BigDecimal" or, still worse, "float", are not adequate for monetary amounts.  We are therefore planning to convert the `Book` layout to use `MPrice` from https://github.com/jpaulm/jbdtypes - which see.  We intended to add a type `PRICE` to the database column definitions, but the MySQL platform does not allow this, so we will have to use `VARCHAR`.  

`Book.java` must be compiled separately from `ReadJDBC`, but the latter will eventually be moved into the JavaFBP libraries, so this will be in one of the following steps.


You can now look at the code for Step15.

Here are the MySQL column definitions:

![Table columns](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step15/docs/columns.png "Table columns")

Here is the definition for Book.java:

```
package com.jpaulmorrison.jdbcstuff.resources.layouts;

import com.jpaulmorrison.jbdtypes.MPrice;  // the jbdtypes jar file must be included in the class path

//import java.math.BigDecimal;  --  no longer needed

public class Book {
	public int id;
	public String title;
	public String author;
	public MPrice price;  // was BigDecimal
	public int    qty;
}
```

There is an underlying assumption in the latest version of `ReadJDBC` - namely that all business types in `JBDTypes` have a "String constructor".  This rule should be adhered to in any future enhancements.
