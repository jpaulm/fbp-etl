FBP-ETL
=======

#### Componentizing ReadJDBC.java (3)

## Diagram from Third Stage

![Output of WriteObjectsToConsole.java](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10-2.png "Output of WriteObjectsToConsole")


## "Componentizing" ReadJDBC.java (continued)   
     

Let us now take a look at step 4 of the 'execute' method.
```
    ResultSet rset = stmt.executeQuery(...);
    
    int rowCount = 0;
     while (rset.next()) {   // Move the cursor to the next row, return false if no more row
        Book book = new Book(); 
        book.title = rset.getString("title");
        book.author = rset.getString("author");
        book.price = rset.getBigDecimal("price");  
        book.qty   = rset.getInt("qty");
      
        outPort.send(create(book));
        ++rowCount;
     }
```

Here is where things start to get complicated (from the point of view of componentization)...!  

The class involved is named "Book", and is defined as follows:

```
public class Book {
	public String title;
	public String author;
	public BigDecimal price;
	public int    qty;
}
```

To pass this to our generalized component, we will have to add another IIP - see diagram below. 

![Adding class name](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/docs/Step12-3.png "Adding class name")

Here is the definition of `books` on the database:

![Column display](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/docs/Step12.png "Column display")

We therefore have:

- 5 fields spread among 3 types

- 5 table columns, whose types have to be compatible with the field definitions

- 3 `ResultSet` methods, corresponding to the 3 field types

The table field types will be a given (there will even be people designing tables that use `float` or `double` for currency amounts - I don't know how to prevent this!).  The class definitions are up to the application designer, so we have more leeway here.

Now, just as Java reflection allows you to obtain metadata, MySQL supports metadata about the columns in a table.  There is no particular reason for ReadJDBC not to obtain all the columns, so we can change the `select` statement to retrieve all columns, and use the MySQL metadata to get names and descriptions for them.

Here is some sample MySQL metadata output (I have included the `select` statement):

![Column metadata](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/docs/Step12-2.png "Column metadata")
 
The column types determine the `ResultSet` `getxx()` methods, so we can use Java reflection to access the columns.

You will notice that, in the code segment above, the Java field names have the same names as the MySQL column names, but we can't rely on this, so I would suggest that the relationships between these - plus any non-standard type information - be held in a JSON file. 

> Now `price` should not be a simple arithmetic type, as it is currency, and should specify the currency denomination.  It is very easy to   assume that a price is in whatever currency we use in our home country, and we have all been doing this for decades in the IT business, but this is not adequate for a worldwide marketplace - see https://jpaulm.github.io/busdtyps.html .  This in turn means that the type of `price` on the database would in fact be `VARCHAR`, and its type in `Book` would be `Currency`.  We will be talking about this in a later step, but it gives an added reason for holding field relationships externally to the component code.  

The latest version of `ReadJDBC.java` is now working. and has table column names as well as object field names factored out. The output is identical to that shown at the bottom of https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/README.md .  

The code can be found in https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/code/components/ReadJDBC.java - what is interesting about this component is that

- there are no table column names or IP object (`Book`) field names hard-wired in the code
- it assumes that the number of table columns and object field names *match*, and *are identical*
- what is (currently) hard-wired is the correspondence between `VARCHAR` and `String`, `DECIMAL` and `BigDecimal`, etc.

Clearly the reader will easily see that these restrictions will not be hard to relax!  As suggested above, these can be addressed by the judicious use of JSON tables.  Stay tuned! 
