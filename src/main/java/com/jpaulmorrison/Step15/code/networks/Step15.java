package com.jpaulmorrison.Step15.code.networks; //change package name, or delete statement, if desired
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
  initialize("[{\"columnName\": \"id\",    \"objField\": \"id\"},   {\"columnName\": \"title\",    \"objField\": \"title\"},   {\"columnName\": \"author\",    \"objField\": \"author\"},   {\"columnName\": \"price\",    \"objField\": \"price\"},   {\"columnName\": \"qty\",    \"objField\": \"qty\"} ]", component("ReadJDBC"), port("FIELDS")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step15().go(); 
} 
}
