package com.jpaulmorrison.Step25.code.networks;

import com.jpaulmorrison.fbp.core.engine.*; 
@ComponentDescription("Load") 
@InPort("IN")

public class Load extends SubNet {
String description = "Load";
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
  initialize("[{\"columnName\": \"id\",    \"objField\": \"id2\"},   {\"columnName\": \"title\",    \"objField\": \"title2\"},   {\"columnName\": \"author\",    \"objField\": \"author2\"},   {\"columnName\": \"price\",    \"objField\": \"price2\"},   {\"columnName\": \"qty\",    \"objField\": \"qty2\"} ]", component("WriteJDBC"), port("FIELDS")); 
  connect(component("SUBIN"), port("OUT"), component("WriteJDBC"), port("IN")); 
} 
}
