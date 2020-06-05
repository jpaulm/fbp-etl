FBP-ETL
=======

#### ETL (Extract-Transform-Load) framework based on JavaFBP

## First Stage Diagram

![Display MySQL Table](https://github.com/jpaulm/fbp-etl/blob/master/Step05/docs/Step05.png "First stage")

##"Componentizing" ReadJDBC.java
     
This is a standard format JavaFBP component, with the usual sections:

- imports
- port attributes
- 'execute' method
- 'openPorts' method

Clearly, only the 'execute' method will be affected by componentizing... unless we add some more IIP ports for additional parameters.

Let us take a look at the 'execute' method - please bring it up in a separate window.

```
 // Step 1: Allocate a database 'Connection' object
	   Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		               "root", pswd);   	
	    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"		               
```

As you can see (by looking at the diagram), the password has already been externalized by putting it in a file, and reading it into a separate (JavaFBP) input port.  

Note that FBP uses the term "port", but the client and server also use the term "port", which in this casee has a standard value of `3306`.  In what follows, "port" will be taken to refer to FBP "ports", unless otherwise specified. 