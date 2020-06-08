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
As we described in Step08, the database name and user ID have been externalized into IIPs.  The table name (`books`) is in the `select` statement above, so we will append that to the database name IIP, separated by an exclamation mark, and ReadJDBC will be modified to split this IIP for the two usages.   See  
https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/code/components/ReadJDBC.java .

You will note I have switched back to `WriteToConsole.java`, as this is not the "main" output of our application and was intended purely for debugging.  However, `WriteToConsole` cannot accept "book"s as input, but it doesn't make sense to turn `Book` into a Java `String`, as we don't know what the next process in the network will be, plus we will probably want to update a table or tables on the output end, so the Book object should be output unchanged.  

So, at this stage, we will need a process to turn `Book` into `String`, as shown in the diagram below (the component name hasn't been filled in as we don't yet know what we will be using here). We may be able to find, or build, a generalized component using Java reflection, or we can just use an ad hoc component to fill this gap. 

![Next Phase](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10.png "Next phase")  

We have built a component called `RoughPacketDisplay.java` which implements the first of these suggestions - it took about 15 minutes to write!  It can be found in https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/jdbcstuff/core/components/RoughPacketDisplay.java .  The output of `ReadJDBC.java` and the input of `RoughPacketDisplay.java` can no longer assume java Strings, so this restriction has been removed from their annotations.

Here is the output:

![Output of RoughPacketDisplay](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10-2.png "Output of RoughPacketDisplay")





