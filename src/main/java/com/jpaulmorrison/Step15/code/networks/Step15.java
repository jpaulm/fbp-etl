package com.jpaulmorrison.Step15.code.networks;
import com.jpaulmorrison.fbp.core.engine.*; 
public class Step15 extends Network {
String description = "Componentization";
protected void define() { 
  component("ReadJDBC",com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  component("Books____to__Strings",com.jpaulmorrison.fbp.core.components.misc.WriteObjectsToConsole.class); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("ReadJDBC"), port("PSWD")); 
  initialize("jdbc:mysql://localhost:3306/ebookshop!books", component("ReadJDBC"), port("DATABASE")); 
  initialize("root", component("ReadJDBC"), port("USER")); 
  connect(component("ReadJDBC"), port("OUT"), component("Books____to__Strings"), port("IN")); 
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Book", component("ReadJDBC"), port("CLASS")); 
  initialize("[{\"colName\": \"id\",    \"objField\": \"id2\"},   {\"colName\": \"title\",    \"objField\": \"title2\"},   {\"colName\": \"author\",    \"objField\": \"author2\"},   {\"colName\": \"price\",    \"objField\": \"price2\"},   {\"colName\": \"qty\",    \"objField\": \"qty2\"} ]", component("ReadJDBC"), port("FIELDS")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step15().go(); 
} 
}
