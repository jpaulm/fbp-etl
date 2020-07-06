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

import jbdtypes.MPrice;  // the jbdtypes jar file must be included in the class path

//import java.math.BigDecimal;  --  no longer needed

public class Book {
	public int id;
	public String title;
	public String author;
	public MPrice price;  // was BigDecimal
	public int    qty;
}
```
Here is the diagram for Step15, modified to show the new access pattern for `Book.java`:

![Access to Book updated](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step15/docs/Step15.png "Access to Book.java updated")

**Note:** There is an underlying assumption in the latest version of `ReadJDBC` - namely that all business types in `JBDTypes` have a "String constructor".  This rule should be adhered to in any future enhancements to JBDTypes.

Here is the output from `WriteObjectsToConsole`:

com.jpaulmorrison.jdbcstuff.resources.layouts.Book: {id: 1001; title: Java for dummies; author: Tan Ah Teck; price: CAD11.11; qty: 11}
com.jpaulmorrison.jdbcstuff.resources.layouts.Book: {id: 1002; title: More Java for dummies; author: Tan Ah Teck; price: CAD22.22; qty: 22}
com.jpaulmorrison.jdbcstuff.resources.layouts.Book: {id: 1003; title: More Java for more dummies; author: Mohammad Ali; price: CAD33.33; qty: 33}
com.jpaulmorrison.jdbcstuff.resources.layouts.Book: {id: 1004; title: A Cup of Java; author: Kumar; price: CAD44.44; qty: 44}
com.jpaulmorrison.jdbcstuff.resources.layouts.Book: {id: 1005; title: A Teaspoon of Java; author: Kevin Jones; price: CAD55.55; qty: 55}

Note the **prices** using the `jbdtypes` convention for `Monetary` and `MPrice`.

This is pretty much the end of development for the "E" end of "ETL"!
