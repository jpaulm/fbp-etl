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

Here is where things start to get complicated (from the point of view of componentization)...!  Partial list of things we have to take care of:

- class named "Book", defined as follows:

```
public class Book {
	public String title;
	public String author;
	public BigDecimal price;
	public int    qty;
}
```

Here is the definition of `books` on the database:

![Column display](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/docs/Step12.png "Column display")

We therefore have:

- 4 fields spread among 3 types

- 5 table columns, whose types have to be compatible with the field definitions

- 3 `ResultSet` methods, corresponding to the 3 field types

Now `price` should not be a simple arithmetic type, as it is currency, and should specify the currency denomination.  Now it is very easy to assume that a price is in whatever currency we use in our home country, and we have all been doing this for decades, but this is not adequate for a worldwide marketplace - see https://jpaulm.github.io/busdtyps.html .  This in turn means that its type on the database would in fact be `VARCHAR`, and its type in `Book` would be `Currency`.  We will be talking about this in a later step.
 
                                                                                              

