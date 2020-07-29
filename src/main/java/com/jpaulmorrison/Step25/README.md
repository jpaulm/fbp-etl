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

![Load](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Extract.png "Load")


At the location where we had a `WriteObjectsToConsole` process, we will use an actual simple Transform component, called `BookSale.java` - you can make it a subnet or not as you wish...  It basically multiplies a price specified using the `MPrice` type from the `jbdtypes` project by a quantity, giving a `Monetary` result.  `jbdtypes` is on Maven, and also in `https://github.com/jpaulm/jbdtypes`.


