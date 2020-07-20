package com.jpaulmorrison.jdbcstuff.resources.layouts;

import jbdtypes.MPrice;
import jbdtypes.Monetary;

//import jbdtypes.*;

//import java.math.BigDecimal;


public class Sale {
	public int id;
	public String title;
	public String author;
	public MPrice price;  // was BigDecimal
	public int    qty;
	public Monetary total;
}