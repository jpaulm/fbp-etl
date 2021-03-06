package com.jpaulmorrison.jdbcstuff.core.components;

import com.jpaulmorrison.fbp.core.engine.Component;
import com.jpaulmorrison.fbp.core.engine.ComponentDescription;
import com.jpaulmorrison.fbp.core.engine.InPort;
import com.jpaulmorrison.fbp.core.engine.InputPort;
import com.jpaulmorrison.fbp.core.engine.OutPort;
import com.jpaulmorrison.fbp.core.engine.OutputPort;
import com.jpaulmorrison.fbp.core.engine.Packet;
import com.jpaulmorrison.jdbcstuff.resources.layouts.Book;
import com.jpaulmorrison.jdbcstuff.resources.layouts.Sale;

//import com.jpaulmorrison.jbdtypes.Quantity;

@ComponentDescription("Convert Book into Sale")
@OutPort("OUT")
@InPort("IN")

public class BookSale extends Component {

	/**
	 * Convert Book into Sale
	 */
	

	  private InputPort inport;

	  private OutputPort outport;

	  @Override
	  protected void execute() {
	    Packet<?> p;
	    while ((p = inport.receive()) != null) {
	    	Book b = (Book)p.getContent();	    	
	    	Sale s = new Sale();	
	    	s.id2 = b.id2;
	    	s.title2 = b.title2;
	    	s.author2 = b.author2;
	    	s.price2 = b.price2;
	    	s.qty2= b.qty2;	    	
	    	s.total2 = b.price2.extend(b.qty2);
	    	outport.send(create(s));
	    	drop(p);
	    }

	  }

	  @Override
	  protected void openPorts() {
	    inport = openInput("IN");

	    outport = openOutput("OUT");
	  }
	}
