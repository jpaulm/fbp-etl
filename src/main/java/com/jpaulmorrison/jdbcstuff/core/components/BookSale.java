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

import jbdtypes.Quantity;

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
	    	s.id2 = b.id;
	    	s.title2 = b.title;
	    	s.author2 = b.author;
	    	s.price2 = b.price;
	    	s.qty2= b.qty;
	    	Quantity q = new Quantity(b.qty);
	    	s.total2 = b.price.extend(q);
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