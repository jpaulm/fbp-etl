FBP-ETL
=======

#### Adding Customized Types to ReadJDBC.java 

So far, we have been using standard data types (SQL and Java).  However, as we have said in a number of publications, "BigDecimal" or, still worse, "float", are not adequate for monetary amounts.  We are therefore planning to convert the `Book` layout to use `MPrice` from https://github.com/jpaulm/jbdtypes - which see.  We will also be adding a type `PRICE` to the database column definitions.  This has necessitated a certain amount of retroactive restructuring to this project.

`Book.java` must be compiled separately from `ReadJDBC`.  Accordingly I have moved it into the `test` directory for the `jbdtypes` project on GitHub (`src/test/java/layouts/Book.java`).  You can use the following command to compile it, after positioning at `GitHub/jbdtypes`:

    javac -cp src/main/**  src/test/java/layouts/Book.java -d bin/test/java/
    
`Book.java` now looks like this:

```
package layouts;

//import java.math.BigDecimal;
import com.jpaulmorrison.jbdtypes.*;

public class Book {
	public int id;
	public String title;
	public String author;
	public MPrice price;
	public int    qty;
}
```

and the resultant class file will be in `bin\test\java\layouts\`.

`Step15.java` will have to be modified to suit.
**Under construction**
