FBP-ETL
=======

#### Componentizing ReadJDBC.java (2)

## Diagram from Second Stage

![Converted to ShowText](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step08/docs/Step08-2.png "Converted to ShowText")

## "Componentizing" ReadJDBC.java (continued)
     

Let us take a look at steps 2 to 4 of the 'execute' method.
```
   // Step 2: Allocate a 'Statement' object in the Connection
   Statement stmt = conn.createStatement();
			   
   // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
   String strSelect = "select title, author, price, qty from **books**";
   ResultSet rset = stmt.executeQuery(strSelect);
			 
  // Step 4: Process the ResultSet by scrolling the cursor forward via next().
  //  For each row, retrieve the contents of the cells with getXxx(columnName).
   int rowCount = 0;
   while(rset.next()) {   // Move the cursor to the next row, return false if no more row
         Book book = new Book(); 
         book.title = rset.getString("title");
         book.author = rset.getString("author");
         book.price = rset.getBigDecimal("price");  
         book.qty   = rset.getInt("qty");
			           
         //String outStr = book.title + ", " + book.author + ", " + book.price + ", " + book.qty;
         //outPort.send(create(outStr));
	
        **outPort.send(create(book));**
   }	               
```

As we described in Step08, the database name and user ID have been externalized into IIPs.  The table name is in the `select` statement above (highlighted), so we will move that to the database name IIP, separated by an exclamation mark... and ReadJDBC will be modified to match. 

It doesn't make sense to turn `Book` into a Java `String`, as we don't know what the next process in the network will be, so the Book should be output as is.  At this stage, therefore, we will need a process to turn `Book` into `String` for now, as shown below. 

![Next Phase](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step10/docs/Step10.png "Next phase")  


