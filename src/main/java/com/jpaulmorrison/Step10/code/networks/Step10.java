package com.jpaulmorrison.Step10.code.networks;
import com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC;
import com.jpaulmorrison.fbp.core.engine.Network;
public class Step10 extends Network {
String description = "Componentization";

protected void define() { 
  component("ReadJDBC",ReadJDBC.class); 
  //component("Display",com.jpaulmorrison.fbp.core.components.misc.WriteToConsole.class); 
  component("Display",com.jpaulmorrison.fbp.core.components.misc.WriteObjectsToConsole.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  connect(component("ReadJDBC"), port("OUT"), component("Display"), port("IN")); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("ReadJDBC"), port("PSWD")); 
  initialize("jdbc:mysql://localhost:3306/ebookshop!books", component("ReadJDBC"), port("DATABASE")); 
  initialize("root", component("ReadJDBC"), port("USER")); 
  //initialize("Selected rows", component("Display"), port("TITLE")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step10().go(); 
} 
}

