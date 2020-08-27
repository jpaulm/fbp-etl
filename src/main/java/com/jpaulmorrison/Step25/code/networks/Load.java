package com.jpaulmorrison.Step25.code.networks; //change package name, or delete statement, if desired
import com.jpaulmorrison.fbp.core.engine.*; 
@ComponentDescription("Load.drw") 
@InPort("IN")

public class Load extends SubNet {
String description = "Load.drw";
protected void define() { 
  component("WriteJDBC",com.jpaulmorrison.fbp.core.components.jdbc.WriteJDBC.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  component("SUBIN",SubIn.class); 
  initialize("IN", component("SUBIN"), port("NAME")); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("WriteJDBC"), port("PSWD")); 
  initialize("jdbc:mysql://localhost:3306/ebookshop!sales", component("WriteJDBC"), port("DATABASE")); 
  initialize("root", component("WriteJDBC"), port("USER")); 
  initialize("com.jpaulmorrison.jdbcstuff.resources.layouts.Sale", component("WriteJDBC"), port("CLASS")); 
  initialize("[{\"colName\": \"id\",    \"objField\": \"id2\"},   {\"colName\": \"title\",    \"objField\": \"title2\"},   {\"colName\": \"author\",    \"objField\": \"author2\"},   {\"colName\": \"price\",    \"objField\": \"price2\"},   {\"colName\": \"qty\",    \"objField\": \"qty2\"} ,   {\"colName\": \"total\",    \"objField\": \"total2\"}  ]", component("WriteJDBC"), port("FIELDS")); 
  connect(component("SUBIN"), port("OUT"), component("WriteJDBC"), port("IN")); 
} 
}
