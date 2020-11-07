FBP-ETL
=======

## ETL (Extract-Transform-Load) framework based on JavaFBP

General web site on Flow-Based Programming: https://jpaulm.github.io/fbp/ .

FBP-ETL is really a kit for building Extract-Transform-Load applications.  We will be showing how a simple network can be developed and expanded using custom- and pre-coded components, to allow endless combinations of databases, files, and network communication, to quickly develop ETL-type applications. 

From the point of view of Flow-Based Programming (FBP) there is nothing special about ETL:  in this project we will be demonstrating a number of the capabilities of FBP in the context of the Extract, Transform and Load type of application, using a mixture of precoded and custom components.

The various phases will be designated `Stepxx`, but the numbers will not necessarily be consecutive.

#### Running Step300

The JavaFBP network `Step300` (held in `Step30`) actually reads a table, makes a simple monetary calculation on each row, and stores the result back in the table.  `Step300` comprises 3 subnets: Extract, Transform, and Load, each of which contains all required parametric information, e.g. table names.  The table password is held in a separate file.

If necessary, go into `services.msc`, and restart MySQL.

We will assume that the required Maven 'artifacts' are all in the user's `.m2` folder.

To run Step300.java from the command line, first run 

`mvn dependency:purge-local-repository clean install`  

then do a `cd` to position to your `fbp-etl\target\classes` directory. 

Then enter 

`java -classpath ".;C:\Users\Paul\.m2\repository\com\jpaulmorrison\javafbp\4.1.11\javafbp-4.1.11.jar;C:\Users\Paul\.m2\repository\com\google\code\gson\gson\2.8.6\gson-2.8.6.jar;C:\Users\Paul\.m2\repository\mysql\mysql-connector-java\8.0.22\mysql-connector-java-8.0.22.jar;C:\Users\Paul\.m2\repository\com\jpaulmorrison\jbdtypes\1.1.3\jbdtypes-1.1.3.jar" com.jpaulmorrison.Step30.code.networks.Step300`  (actual class name can use periods or slashes...)

replacing `Paul` by your user name!   Also you may need to update some of the version numbers - check with Maven.

### Steps

[Step05 - First phase - little externalization](src/main/java/com/jpaulmorrison/Step05/)

[Step08 - Componentizing ReadJDBC.java](src/main/java/com/jpaulmorrison/Step08/)

[Step10 - Componentizing ReadJDBC.java (2)](src/main/java/com/jpaulmorrison/Step10/)

[Step12 - Componentizing ReadJDBC.java (3)](src/main/java/com/jpaulmorrison/Step12/)

[Step14 - Componentizing ReadJDBC.java (4)](src/main/java/com/jpaulmorrison/Step14/)

[Step15 - Adding customized data types](src/main/java/com/jpaulmorrison/Step15/)

**Note:** at this point (Step15), the data was changed to contain currency indications, as per https://github.com/jpaulm/jbdtypes , so to run earlier steps, the data table prices will have to be changed back to simple integers.scalars.

[Step20 - Develop WriteJDBC and start "Two Level" Structuring](src/main/java/com/jpaulmorrison/Step20/)

[Step25 - Transform and "Load" section of ETL - top-down and bottom-up](src/main/java/com/jpaulmorrison/Step25/)

[Step30 - Externalising Password block](src/main/java/com/jpaulmorrison/Step30/)



