FBP-ETL
=======

#### Develop WriteJDBC, Add Transform and "Load" section of ETL

So far, we have developed our network in a rather methodical, slow and plodding way!  I am sure you are tired of this, and want to see what the final network looks like!  

There are several ways we could go about this, bearing in mind that the network diagram is already rather complex.  We could develop the existing network visually, which will almost certainly go off the side of the screen! :-)  Luckily FBP has the "subnet" concept, so we could design the network as a number of subnets - either by hand, or using the "Excise" function of DrawFBP. In fact, that is the way that the network will eventually be structured.

To be contrary, and also to show that it is pretty simple to do, I decided to code up `WriteJDBC`, using a lot of the code from `ReadJDBC`, and code the rest of the network by hand.  `WriteJDBC` has been moved to the `JavaFBP` project.   The resulting code can accordingly be found at https://github.com/jpaulm/javafbp/blob/master/src/main/java/com/jpaulmorrison/fbp/core/components/jdbc/WriteJDBC.java and https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Step20.java .

In the latter, you will note that I have sometimes used the "shorthand" version of `connect`, e.g.:
   
    connect("Repl_2.OUT[0]", component("ReadJDBC"), port("USER")); 
  
where process name and port name are in the same string, separated by a period.  

Between the `ReadJDBC` and `WriteJDBC`, I have added a very simple "Transform" process: `BookSale`, which "extends" each book's quantity by the price, generating a `total` $ CAD amount, using Business Data types `MPrice` and `Monetary`.  It can be found at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/components/BookSale.java . 

Now, I fully agree that the network is hard to read, so we will now (belatedly!) develop the diagram, eventually using subnets for legibility, and test that it produces the same results as the hand-coded version.

The generated MySQL `sales` table looks like this - our final output should look exactly the same! 

![Sales table](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/sales.png "Sales table")

You will remember in Step15, we put up this diagram:

![Access to Book updated](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step15/docs/Step15.png "Access to Book.java updated")

