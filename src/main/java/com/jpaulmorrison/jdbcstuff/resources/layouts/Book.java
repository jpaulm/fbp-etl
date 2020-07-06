package com.jpaulmorrison.jdbcstuff.resources.layouts;

import com.jpaulmorrison.jbdtypes.MPrice;

//import jbdtypes.*;

//import java.math.BigDecimal;


public class Book {
	public int id;
	public String title;
	public String author;
	public MPrice price;  // was BigDecimal
	public int    qty;
}