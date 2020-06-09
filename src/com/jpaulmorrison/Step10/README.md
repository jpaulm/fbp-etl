FBP-ETL
=======

#### Componentizing ReadJDBC.java (2)

## Diagram from Second Stage

![Converted to ShowText](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step08/docs/Step08-2.png "Converted to ShowText")

## "Componentizing" ReadJDBC.java (continued)
     

Let us take a look at steps 2 and 3 of the 'execute' method.
```
   // Step 2: Allocate a 'Statement' object in the Connection
   Statement stmt = conn.createStatement();
			   
   // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
   String strSelect = "select title, author, price, qty from books";  // <== 'books' is table name
   ResultSet rset = stmt.executeQuery(strSelect);
```
As we described in Step08, the database name and user ID have been externalized into IIPs.  The table name (`books`) is in the `select` statement above, but should also be externalized, so we will append that to the database name IIP, separated by an exclamation mark, and ReadJDBC will be modified to split this IIP for the two usages.   See  

https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/code/components/ReadJDBC.java .

For now, we will still list the table rows (on the console), but it doesn't make sense to turn `Book` into a Java `String`, as we don't know what the next process in the network will be, plus we will probably want to update a table or tables on the output end, which will need "book"s unchanged, so the Book object should be output as is.  

`WriteToConsole` cannot accept "book" objects as input, so we will add a process to turn `Book` into `String`, as shown in the diagram below.  We could build an ad hoc component to fill this gap, but in fact we can use reflection to build a generalized component, which not only displays the fields, but also shows their names as well, plus the class name.

![Next Phase](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10.png "Next phase")  

We have built a component called `WriteObjectsToConsole.java` which implements the latter suggestion - it took about 20 minutes to write!  It can be found in https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/jdbcstuff/core/components/WriteObjectsToConsole.java.  The output of `ReadJDBC.java` and the input of `WriteObjectsToConsole.java` can no longer assume Java Strings, so this restriction has been removed from their annotations.

Here is a sample of the output from `WriteObjectsToConsole.java`:

![Output of WriteObjectsToConsole.java](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10-2.png "Output of WriteObjectsToConsole")

