package com.jpaulmorrison.Step15.code.networks;
import com.jpaulmorrison.fbp.core.engine.*; 
public class Step15 extends Network {
String description = "Componentization";
protected void define() { 
  component("ReadJDBC",com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  component("Books____to__Strings",com.jpaulmorrison.jdbcstuff.core.components.WriteObjectsToConsole.class); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("ReadJDBC"), port("PSWD")); 
  initialize("jdbc:mysql://localhost:3306/ebookshop!books", component("ReadJDBC"), port("DATABASE")); 
  initialize("root", component("ReadJDBC"), port("USER")); 
  connect(component("ReadJDBC"), port("OUT"), component("Books____to__Strings"), port("IN")); 
  //initialize("com.jpaulmorrison.Step15.code.layouts.Book", component("ReadJDBC"), port("CLASS")); 
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Book", 
      component("ReadJDBC"), port("CLASS")); 
  initialize("[{\"colName\": \"id\",    \"objField\": \"id\"},   {\"colName\": \"title\",    \"objField\": \"title\"},   {\"colName\": \"author\",    \"objField\": \"author\"},   {\"colName\": \"price\",    \"objField\": \"price\"},   {\"colName\": \"qty\",    \"objField\": \"qty\"} ]", 
		     component("ReadJDBC"), port("FIELDS")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step15().go(); 
} 
}
