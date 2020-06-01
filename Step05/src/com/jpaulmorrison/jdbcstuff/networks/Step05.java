package com.jpaulmorrison.jdbcstuff.networks; //change package name, or delete statement, if desired
import com.jpaulmorrison.fbp.core.engine.*; 
public class Step05 extends Network {
String description = "Click anywhere on selection area";
protected void define() { 
  component("ReadJDBC",com.jpaulmorrison.jdbcstuff.core.components.ReadJDBC.class); 
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
