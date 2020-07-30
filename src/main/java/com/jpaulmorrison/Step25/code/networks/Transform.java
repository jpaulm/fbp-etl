package com.jpaulmorrison.Step25.code.networks;

import com.jpaulmorrison.fbp.core.engine.*; 
@ComponentDescription("Transform.drw") 
@InPort("IN")
@OutPort("OUT")

public class Transform extends SubNet {
String description = "Transform.drw";
protected void define() { 
  component("BookSale",com.jpaulmorrison.jdbcstuff.core.components.BookSale.class); 
  component("SUBIN",SubIn.class); 
  initialize("IN", component("SUBIN"), port("NAME")); 
  component("SUBOUT",SubOut.class); 
  initialize("OUT", component("SUBOUT"), port("NAME")); 
  connect(component("SUBIN"), port("OUT"), component("BookSale"), port("IN")); 
  connect(component("BookSale"), port("OUT"), component("SUBOUT"), port("IN" )); 
} 
}
