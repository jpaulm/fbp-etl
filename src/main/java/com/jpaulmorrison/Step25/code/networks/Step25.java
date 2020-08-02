package com.jpaulmorrison.Step25.code.networks; //change package name, or delete statement, if desired
import com.jpaulmorrison.fbp.core.engine.*; 
public class Step25 extends Network {
String description = "ETL High level #2";
protected void define() { 
  component("Extract",com.jpaulmorrison.Step25.code.networks.Extract.class); 
  component("Transform",com.jpaulmorrison.Step25.code.networks.Transform.class); 
  component("Load",com.jpaulmorrison.Step25.code.networks.Load.class); 
  connect(component("Extract"), port("OUT"), component("Transform"), port("IN")); 
  connect(component("Transform"), port("OUT"), component("Load"), port("IN")); 
} 
public static void main(String[] argv) throws Exception  { 
  new Step25().go(); 
} 
}
