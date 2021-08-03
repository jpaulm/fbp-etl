package com.jpaulmorrison.jdbcstuff.resources.layouts;

/*******************************************************************************************
*                                                                                          *
* 'jbdtypes' is Java Business Data Types - get jar file from Maven (search for jbdtypes)   *
*                                                                                          *
*******************************************************************************************/


import com.jpaulmorrison.jbdtypes.MPrice;
import com.jpaulmorrison.jbdtypes.Monetary;
import com.jpaulmorrison.jbdtypes.Quantity;



//import java.math.BigDecimal;


public class Sale {
	public int id2;
	public String title2;
	public String author2;
	public MPrice price2;  // was BigDecimal
	public Quantity qty2;
	public Monetary total2;
	
	public Sale() {
		title2 = new String();
		author2 = new String();
		price2 = new MPrice("CAD0.00");
		qty2 = new Quantity(0);
		total2 = new Monetary("CAD0.00");		
	}
}
