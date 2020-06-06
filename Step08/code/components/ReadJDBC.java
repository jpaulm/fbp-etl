package com.jpaulmorrison.jdbcstuff.core.components;

import java.sql.*;

import com.jpaulmorrison.fbp.core.engine.Component;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import com.jpaulmorrison.fbp.core.engine.ComponentDescription;
import com.jpaulmorrison.fbp.core.engine.InPort;
import com.jpaulmorrison.fbp.core.engine.InPorts;
import com.jpaulmorrison.fbp.core.engine.InputPort;
import com.jpaulmorrison.fbp.core.engine.OutPort;
import com.jpaulmorrison.fbp.core.engine.OutputPort;
import com.jpaulmorrison.fbp.core.engine.Packet;
import com.jpaulmorrison.jdbcstuff.resources.layouts.*;

@ComponentDescription("Reads table from MySQL and outputs result")
@InPorts({ 
@InPort(value = "DATABASE", description = "Database name", type = String.class),
@InPort(value = "USER", description = "User name", type = String.class),
@InPort(value = "PSWD", description = "Password obtained from file", type = String.class)})
@OutPort(value = "OUT", description = "Table rows", type = String.class)

public class ReadJDBC extends Component {
	

	// adapted from https://www.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html
	
	  private OutputPort outPort;

	  private InputPort pswdPort;
	  private InputPort dBNPort;
	  private InputPort userPort;

	@Override
	protected void execute() throws Exception {
		
		Packet<?> pp = pswdPort.receive();
		
		String pswd = (String) pp.getContent();
		drop(pp);
		pswdPort.close();
		
		pp = dBNPort.receive();
		
		String database = (String) pp.getContent();
		drop(pp);
		dBNPort.close();
		
		pp = userPort.receive();
		
		String user = (String) pp.getContent();
		drop(pp);
		userPort.close();
		
	      try (
			         // Step 1: Allocate a database 'Connection' object
			         Connection conn = DriverManager.getConnection(
			               //"jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
			               //"root", pswd);   // For MySQL only
			        		database + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", user, pswd); 
			               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
			 
			         // Step 2: Allocate a 'Statement' object in the Connection
			         Statement stmt = conn.createStatement();
			      ) {
			         // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
			         String strSelect = "select title, author, price, qty from books";
			         //System.out.println("The SQL statement is: \"" + strSelect + "\"\n"); // Echo For debugging
			         outPort.send(create("The SQL statement is: \"" + strSelect + "\"\n")); // Echo For debugging
			 
			         ResultSet rset = stmt.executeQuery(strSelect);
			 
			         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
			         //  For each row, retrieve the contents of the cells with getXxx(columnName).
			         //System.out.println("The records selected are:");
			         outPort.send(create("The records selected are:"));
			         int rowCount = 0;
			         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
			        	Book book = new Book(); 
			            book.title = rset.getString("title");
			            book.author = rset.getString("author");
			            book.price = rset.getBigDecimal("price");  
			            book.qty   = rset.getInt("qty");
			            //System.out.println(book.title + ", " + book.author + ", " + book.price + ", " + book.qty);
			            String outStr = book.title + ", " + book.author + ", " + book.price + ", " + book.qty;
			            outPort.send(create(outStr));
			            ++rowCount;
			         }
			         //System.out.println("Total number of records = " + rowCount);
			         outPort.send(create("Total number of records = " + rowCount));
			 
			      } catch(SQLException ex) {
			         ex.printStackTrace();
			      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
	}

	@Override
	protected void openPorts() {
		  pswdPort = openInput("PSWD");
		  userPort = openInput("USER");
		  dBNPort = openInput("DATABASE");
		  outPort = openOutput("OUT");		
	}
}
