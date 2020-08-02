FBP-ETL
=======

#### Develop WriteJDBC and start "Two Level" Structuring

So far, we have developed our network in a rather methodical, slow and plodding way!  I am sure you are tired of this, and want to see what the final network looks like!  

There are several ways we could go about this, bearing in mind that the network diagram is already rather complex.  We could develop the existing network visually, which will almost certainly go off the side of the screen! :-)  Luckily FBP has the "subnet" concept, so we could design the network as a number of subnets - either by hand, or using the "Excise" function of DrawFBP. In fact, that is the way that the network will eventually be structured.

To be contrary, and also to show that it is pretty simple to do, I decided to code up `WriteJDBC`, using a lot of the code from `ReadJDBC`, and code the rest of the network by hand.  `WriteJDBC` has been moved to the `JavaFBP` project.   The resulting code can accordingly be found at https://github.com/jpaulm/javafbp/blob/master/src/main/java/com/jpaulmorrison/fbp/core/components/jdbc/WriteJDBC.java and the final `Step20` network can be found at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Step20.java .

In the latter, you will note that I have sometimes used the "shorthand" version of `connect`, e.g.:
   
    connect("Repl_2.OUT[0]", component("ReadJDBC"), port("USER"));  
    
or 

    connect("Repl_2.OUT[0]", "ReadJDBC.USER")); 
  
where process name and port name are in the same string, separated by a period.  

Between the `ReadJDBC` and `WriteJDBC`, I have added a very simple "Transform" process: `BookSale`, which "extends" each book's quantity by the price, generating a `total` $ CAD amount, using Business Data types `MPrice` and `Monetary`.  It can be found at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/components/BookSale.java . 

Now, I fully agree that the network is hard to read, so we will now (belatedly!) develop the diagram, eventually using subnets for legibility, and test that it produces the same results as the hand-coded version.

The generated MySQL `sales` table looks like this - our final output should look exactly the same! 

![Sales table](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/sales.png "Sales table")

You will remember in Step15, we put up this diagram:

![Access to Book updated](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step15/docs/Step15.png "Access to Book.java updated")

Now we are going to do some magic!  The diagram is a bit complex, so we are going to show it as two levels: a high-level design and the Extract portion.  We are going to use a neat (IMHO) DrawFBP facility called "Enclosure Excise" where we surround a chunk of diagram with the Enclosure block, stretching the corners to enclose the piece we want to excise, and then "snip"! - the excised diagram now has "sticky" connections, and the original network has the excised blocks and arrows replaced by a single "subnet" block (shown with a double line border).

Unfortunately in this case, we will have to play games with the long IIP, as the Excise boundary will go right through it: I therefore decided to remove it temporarily first and then add it back later!  We now have two diagrams, shown here:

Top level:

![Level #1](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/Step20-1.png "Level #1")

Extract subnet:

![Extract](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/Extract.png "Extract")

The "External Port" symbol with the word `OUT` under it in red may be thought of as a "sticky" connection.  This will actually be implemented in the generated code by a specialized component called `SubOut`. You can find the generated code for `Extract` at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Extract.java .

Not only is this easier to read, but you can see the current structure of the application, and can imagine how it is going to develop.  At the point where the block labeled "Books to Strings" is shown in the high level diagram we will be adding a "Transform" subnet and a "Load" subnet.

Plus, if you do this using DrawFBP, you an jump back and forth between the top level and the various subnets.  

In the diagram labelled "High level #1", you can see that the leftmost block has a double line around it, indicating that it is a subnet, and has both a diagram name (shown in green), and will eventually have a component name (in blue), as it is both a network and a component.

In the next step, we will add a (trivial) Transform subnet, and the "Load" subnet. We will be working both "bottom up" and "top down" - hopefully this will make perfect sense!   



