package com.jpaulmorrison.Step20.code.components;

import com.jpaulmorrison.fbp.core.engine.Component;
import com.jpaulmorrison.fbp.core.engine.ComponentDescription;
import com.jpaulmorrison.fbp.core.engine.InPort;
import com.jpaulmorrison.fbp.core.engine.InputPort;
import com.jpaulmorrison.fbp.core.engine.OutPort;
import com.jpaulmorrison.fbp.core.engine.OutputPort;
import com.jpaulmorrison.fbp.core.engine.Packet;
import com.jpaulmorrison.jdbcstuff.resources.layouts.Book;
import com.jpaulmorrison.jdbcstuff.resources.layouts.Sale;

import jbdtypes.MPrice;
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
	    	s.id = b.id;
	    	s.title = b.title;
	    	s.author = b.author;
	    	s.price = b.price;
	    	s.qty= b.qty;
	    	Quantity q = new Quantity(b.qty);
	    	s.total = b.price.extend(q);
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
