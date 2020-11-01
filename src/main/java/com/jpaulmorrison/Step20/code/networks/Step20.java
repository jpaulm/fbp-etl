package com.jpaulmorrison.Step20.code.networks;

import com.jpaulmorrison.fbp.core.engine.*; 
public class Step20 extends Network {
String description = "Componentization";
protected void define() { 
  component("ReadJDBC",com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  //component("Books____to__Strings",com.jpaulmorrison.fbp.core.components.misc.WriteObjectsToConsole.class);
  component("WriteJDBC",com.jpaulmorrison.fbp.core.components.jdbc.WriteJDBC.class); 
  component("BookSale",com.jpaulmorrison.jdbcstuff.core.components.BookSale.class); 
  
  component("Repl_1",com.jpaulmorrison.fbp.core.components.text.ReplString.class);
  component("Repl_2",com.jpaulmorrison.fbp.core.components.text.ReplString.class);
  
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  
  initialize("jdbc:mysql://localhost:3306/ebookshop!books", component("ReadJDBC"), port("DATABASE")); 
  
  connect("Read__pswd.OUT", "Repl_1.IN");
  connect("Repl_1.OUT[0]", component("ReadJDBC"), port("PSWD")); 
  connect("Repl_1.OUT[1]", component("WriteJDBC"), port("PSWD")); 
  
  initialize("root", component("Repl_2"), port("IN")); 
  connect("Repl_2.OUT[0]", component("ReadJDBC"), port("USER")); 
  connect("Repl_2.OUT[1]", component("WriteJDBC"), port("USER")); 
  
  connect(component("ReadJDBC"), port("OUT"), component("BookSale"), port("IN"));
  //connect(component("BookSale"), port("OUT"), component("Books____to__Strings"), port("IN"));
  
  //connect(component("Books____to__Strings"), port("OUT"), component("WriteJDBC"), port("IN"));
 
  connect(component("BookSale"), port("OUT"),  component("WriteJDBC"), port("IN"));
  
  initialize("jdbc:mysql://localhost:3306/ebookshop!sales", component("WriteJDBC"), port("DATABASE")); 
  
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Book", 
      component("ReadJDBC"), port("CLASS")); 
  initialize("[{\"colName\": \"id\",    \"objField\": \"id2\"},   "
  		+ "{\"colName\": \"title\",    \"objField\": \"title2\"},  "
  		+ " {\"colName\": \"author\",    \"objField\": \"author2\"},  "
  		+ " {\"colName\": \"price\",    \"objField\": \"price2\"},   "
  		+ "{\"colName\": \"qty\",    \"objField\": \"qty2\"} ]", 
		     component("ReadJDBC"), port("FIELDS")); 
  
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Sale", 
	      component("WriteJDBC"), port("CLASS")); 
  initialize("[{\"colName\": \"id\",    \"objField\": \"id2\"},   "
		+  "{\"colName\": \"qty\",    \"objField\": \"qty2\"}, "  // try to force bug
  		+ "{\"colName\": \"title\",    \"objField\": \"title2\"},   "
  		+ "{\"colName\": \"author\",    \"objField\": \"author2\"},   "
  		+ "{\"colName\": \"price\",    \"objField\": \"price2\"},   "
  		//+ "{\"colName\": \"qty\",    \"objField\": \"qty2"}, "
  		+ "{\"colName\": \"total\",    \"objField\": \"total2\"} ]", 
			     component("WriteJDBC"), port("FIELDS")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step20().go(); 
} 
}
