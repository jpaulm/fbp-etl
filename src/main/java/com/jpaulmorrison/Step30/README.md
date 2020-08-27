FBP-ETL
=======

####  Exercise(s) for the reader

If you look at the following two diagrams, you will see that they both obtain the password from a file...

![Extract](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Extract.png "Extract")

![Load](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Load.png "Load")

Even if the two tables involved require different passwords, they should probably both be obtained from the same place...


If you look at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Step20.java , you will see that we did exactly that: for both the (single) password, and for the user name.  

Now, since "Extract" and "Load" are separate subnets, we will have to add another input port to each of these, and move the password reader outside of them - i.e. to the higher level.

<hr>

However, first we will show the high-level diagram for this ETL app, with password access fully within the first and 3rd blocks - as below.

![High Level Diagram](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step30/docs/Step30-1.png "High Level Diagram")

Thses are all subnets, so, when using DrawFBP, double clicking on each name in green will bring up the next lower level diagram...  The higher level remains in a separate tab, so you can jump back and forth easily.  The blue class names can be used to bring up class attributes, both for subnets and for lowest level components.







