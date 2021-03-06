package com.jpaulmorrison.Step05.code.networks; //change package name, or delete statement, if desired
import com.jpaulmorrison.fbp.core.components.jdbc.ReadJDBC;
import com.jpaulmorrison.fbp.core.engine.*;  
public class Step05 extends Network {
String description = "First stage of FBP-ETL development";
protected void define() { 
  component("ReadJDBC",ReadJDBC.class); 
  component("Display",com.jpaulmorrison.fbp.core.components.misc.WriteToConsole.class); 
  component("Read__pswd",com.jpaulmorrison.fbp.core.components.io.ReadFile.class); 
  connect(component("ReadJDBC"), port("OUT"), component("Display"), port("IN")); 
  initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("Read__pswd"), port("SOURCE")); 
  connect(component("Read__pswd"), port("OUT"), component("ReadJDBC"), port("PSWD")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step05().go(); 
} 
}
