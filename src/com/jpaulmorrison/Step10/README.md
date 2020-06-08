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
   String strSelect = "select title, author, price, qty from <bold>books</bold>";
   ResultSet rset = stmt.executeQuery(strSelect);
```
As we described in Step08, the database name and user ID have been externalized into IIPs.  The table name (`books`) is in the `select` statement above (highlighted), so we will append that to the database name IIP, separated by an exclamation mark, and ReadJDBC will be modified to split this IIP for the two usages.   See  

It doesn't make sense to turn `Book` into a Java `String`, as we don't know what the next process in the network will be, plus we will probably want to update a table or tables on the output end, so the Book object should be output unchanged.  At this stage, therefore, we will need a process to turn `Book` into `String`, as shown in the diagram below (the component name hasn't been filled in as e don't yet know what we will be using here). 

![Next Phase](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10.png "Next phase")  

You will note I have switched back to `WriteToConsole.java`, as this is not the "main" output of our application.


