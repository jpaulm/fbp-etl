FBP-ETL
=======

#### Develop WriteJDBC, Add Transform and "Load" section of ETL

So far, we have developed our network in a rather methodical, slow and plodding way!  I am sure you are tired of this, and want to see what the final network looks like!  

There are several ways we could go about this, bearing in mind that the network diagram is already rather complex.  We could devlop the existing network visually, which will almost certainly go off the side of the screen.  :-)  Luckily we have the "subnet" concept, so we could design the network as a number of subnets - either by hand, or using the "Excise" function of DrawFBP.

To be contrary, and also to show that it is pretty simple, I decided to code up `WriteJDBC`, using a lot of the code from `ReadJDBC` and code the rest of the network by hand.  The resulting code can be found at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/components/WriteJDBC.java and https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Step20.java .

Between the `ReadJDBC` and `WriteJDBC`, I have added a very simple "Transform" process: `BookSale`, which "extends" each book's quantity by the price, using Business Data types `MPrice` and `Monetary`.  It can be found at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/components/BookSale.java . 

Now, I fully agree that the network is hard to read, so we will develop the diagram, eventually using subnets for legibility, and test that it produces the same results.

The generated MySQL `sales` table looks like this: 

![Sales table](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/sales.png "Sales table")
