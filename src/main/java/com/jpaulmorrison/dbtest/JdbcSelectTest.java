import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

// from https://www.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html
 
public class JdbcSelectTest {   
  
   
	public static void main(String[] args) {
		
	      try (
	         // Step 1: Allocate a database 'Connection' object
	         Connection conn = DriverManager.getConnection(
	               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
	               "root", "monTwi*99Fr");   // For MySQL only
	               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
	 
	         // Step 2: Allocate a 'Statement' object in the Connection
	         Statement stmt = conn.createStatement();
	      ) {
	         // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
	         String strSelect = "select title, author, price, qty from books";
	         System.out.println("The SQL statement is: \"" + strSelect + "\"\n"); // Echo For debugging
	 
	         ResultSet rset = stmt.executeQuery(strSelect);
	 
	         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
	         //  For each row, retrieve the contents of the cells with getXxx(columnName).
	         System.out.println("The records selected are:");
	         int rowCount = 0;
	         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	        	Book book = new Book(); 
	            book.title = rset.getString("title");
	            book.author = rset.getString("author");
	            book.price = rset.getDouble("price");  
	            book.qty   = rset.getInt("qty");
	            System.out.println(book.title + ", " + book.author + ", " + book.price + ", " + book.qty);
	            ++rowCount;
	         }
	         System.out.println("Total number of records = " + rowCount);
	 
	      } catch(SQLException ex) {
	         ex.printStackTrace();
	      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
   }
   
  
}