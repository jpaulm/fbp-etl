FBP-ETL
=======


## Diagram from First Stage

![Display MySQL Table](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step05/docs/Step05.png "First stage")

## "Componentizing" ReadJDBC.java
     
This is a standard format JavaFBP component, with the usual sections:

- imports
- port attributes
- 'execute' method
- 'openPorts' method

Let us take a look at the 'execute' method - please bring it up in a separate window.

```
 // Step 1: Allocate a database 'Connection' object
	   Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		               "root", pswd);   	
	    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"		               
```

As you can see (see the diagram above), the password has already been externalized by putting it in a file, and reading the contents into a separate (JavaFBP) input port.  

**Note that FBP uses the term "port", but the client and server also use the term "port", which in this case has a standard value of `3306`.  In what follows, "port" will be taken to refer to FBP "ports", unless otherwise specified.** 

In the Connection statement above, we suggest that all the parts be obtained from different input ports, with database name and user name perhaps being in IIPs.  Of course, FBP allows them to be obtained from any source.  The diagram now becomes the following: 

![Starting to componentize](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step08/docs/Step08.png "Starting to componentize ReadJDBC")

Note that the password is coming in from a file, while the other two parameters are in IIPs - as far as the network is concerned.  Of course `ReadJDBC` doesn't care - the designer is free to obtain these three parameters from any source s/he likes...  We (the network designer) decided to put the password in a separate file simply to provide better security...  We could for instance insert an encryption component between the ReadFile process and ReadJDBC.

We now have to update `ReadJDBC.java` to receive from the additional input ports.  Since `ReadJDBC.java` will be going through a number of iterations, we will put successive versions in the appropriate `code` directory for a given `Stepxx` directory.

Updating `ReadJDBC.java`, up to this point, is very straightforward, as we just have to add two annotations, two `openInput` calls, and the logic to `receive` the additional inputs. One glitch: if you have more than one `@InPort` annotation, they have to be surrounded with an `@InPorts` annotation.  See https://github.com/jpaulm/fbp-etl/blob/master/Step08/code/components/ReadJDBC.java .

The output of the run is of course exactly the same as in `Step05`.

## Change display process to ShowText

This is a minor change: JavaFBP ShowText uses the JavaSwing JFrame class, so the output appears in a separate window.  ShowText also requires a title, specified in an IIP attached to its `TITLE` port.  The revised diagram is now as follows:

![Converted to ShowText](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step08/docs/Step08-2.png "Converted to ShowText")

This diagram was used to generate a network, which was then run - with the selected rows appearing in a separate window.

If you take a closer look, you will notice that the two lines "The SQL statement is..." and "Total number of records..." are separated from the output of the MySQL `select` statement, so that the former appear on the debugging console. 

Combining these outputs into one image, this will look roughly as follows (I have put a border around this extracted diagram for reading ease):

![Combined Output](https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step08/docs/Step08-3.png "Combined Output")

