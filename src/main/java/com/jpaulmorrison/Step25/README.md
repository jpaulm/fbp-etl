FBP-ETL
=======

####  Transform and "Load" sections of ETL - top-down and bottom-up

In the previous step we put up two diagrams - shown below:

Top level:

![Level #1](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/Step20-1.png "Level #1")

Extract subnet:

![Extract](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/docs/Extract.png "Extract")


We have also magically created (by hand) the "Load" general component https://github.com/jpaulm/javafbp/blob/master/src/main/java/com/jpaulmorrison/fbp/core/components/jdbc/WriteJDBC.java , so you can visualize that our "ETL" diagram will have an "Extract" subnet at one end, and a "Load" subnet at the other, with something in the middle.  I our earlier networks we put a `WriteObjectsToConsole` component there, so we could see what was being generated. We will drop this, as there is not much point in leaving that there - in fact what we really want to do is display the objects *after* they have been written to the `sales` table.  More about that later.

Here is the "Load" subnet - not surprisingly, almost identical to the "Extract" subnet, except that it has an `IN` port, instead of an `OUT` port.

![Load](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Load.png "Load")


At the location where we had a `WriteObjectsToConsole` process, we will use an actual simple Transform component, called `BookSale.java` - you can make it a subnet or not as you wish...  It basically multiplies a price specified using the `MPrice` type from the `jbdtypes` project by a quantity, giving a `Monetary` result.  `jbdtypes` is on Maven, and also in `https://github.com/jpaulm/jbdtypes`.

We didn't really have to make "Transform" a subnet, since it only contains one component, but for uniformity we will do that - so here it is:

![High Level](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Step25.png "Load") ,

and here is the "Transform" subnet:

![Transform](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Transform.png "Load") 

So we now have all the diagrams.  Note that the "Step25" diagram has subnet *names* filled in, but no component names - these will have to be filled in after the three subnets have been compiled...  

### Running a program with subnets

So far, we have been able to simply run our applications under Eclipse without too much fiddling!  However, the new structure with subnets requires a bit more work!

To generate a running program, perform the following steps:

- First, make sure that the output directory for your project is `bin`, not `target\classes` - this is to maintain compatibilty with DrawFBP's "Compile" facility.  It is easy to change it, if it was not set up this way to begin with!  The `bin` specification must match the `src`: i.e. if `src` is `src\main\java`, `bin` must be specified as `bin/main/java'.

- Generate Java programs for all subnets.

- Go into Eclipse to fill in correct **package** names

- Add `jbdtypes` latest jar file using DrawFBP "Add Additional Jar Files" - you should find it in your `.m2/repository/com/jpaulmorrison/jbdtypes` folder.

- Use DrawFBP "Compile" function to compile, in this order: Book.java, Sale.java, BookSale.java






