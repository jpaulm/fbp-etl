package com.jpaulmorrison.Step20.code.networks;

import com.jpaulmorrison.fbp.core.engine.*; 
@ComponentDescription("Extract.drw") 
@OutPort("OUT")

public class Extract extends SubNet {
String description = "Extract.drw";
protected void define() { 
  component("ReadJDBC",com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC.class); 
  component("SUBOUT",SubOut.class); 
  initialize("OUT", component("SUBOUT"), port("NAME")); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("ReadJDBC"), port("PSWD")); 
  initialize("jdbc:mysql://localhost:3306/ebookshop!books", component("ReadJDBC"), port("DATABASE")); 
  initialize("root", component("ReadJDBC"), port("USER")); 
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Book", component("ReadJDBC"), port("CLASS")); 
  connect(component("ReadJDBC"), port("OUT"), component("SUBOUT"), port("IN" )); 
  initialize("[{\"columnName\": \"id\",    \"objField\": \"id\"},   {\"columnName\": \"title\",    \"objField\": \"title\"},   {\"columnName\": \"author\",    \"objField\": \"author\"},   {\"columnName\": \"price\",    \"objField\": \"price\"},   {\"columnName\": \"qty\",    \"objField\": \"qty\"} ]", component("ReadJDBC"), port("FIELDS")); 
} 
}
