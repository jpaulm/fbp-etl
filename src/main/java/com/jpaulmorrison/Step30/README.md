FBP-ETL
=======

####  Exercise(s) for the reader

If you look at the following two diagrams, you will see that they both obtain the password from a file...

![Extract](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Extract.png "Extract")

![Load](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step25/docs/Load.png "Load")

Even if the two tables involved require different passwords, they should probably both be obtained from the same place...


If you look at https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step20/code/networks/Step20.java , you will see that we did exactly that: for both the (single) password, and for the user name.  

Now, since "Extract" and "Load" are separate subnets, we will have to add another input port to each of these, and move the password reader outside of them - i.e. to the higher level. 







