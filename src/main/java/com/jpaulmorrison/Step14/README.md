FBP-ETL
=======

#### Componentizing ReadJDBC.java (3)


## "Componentizing" ReadJDBC.java (continued)   
     

In the previous step, we said in connection with the code in https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step12/code/components/ReadJDBC.java that

- there are no table column names or IP object (`Book`) field names hard-wired in the code
- what is (currently) hard-wired is the correspondence between `VARCHAR` and `String`, `DECIMAL` and `BigDecimal`, etc.

We are going to try to relax these constraints.

First, we are going to add a JSON file of correspondences between table columns and `Book` fields.  This will be passed to `ReadJDBC` in a separate input port.  The IP contents will look like this:

```
[{"colName": "id", 
  "objField": "id2"},
  {"colName": "title", 
  "objField": "title2"},
  {"colName": "author", 
  "objField": "author2"},
  {"colName": "price", 
  "objField": "price2"},
  {"colName": "qty", 
  "objField": "qty2"}
]

```

For convenience, we will put this in an IIP - however, as far as `ReadJDBC` is concerned, it could be in an IIP, or on a file - it doesn't care.

So here is our network, with the field correspondences added in:

![Adding correspondences](https://github.com/jpaulm/fbp-etl/blob/master/src/main/java/com/jpaulmorrison/Step14/docs/Step14.png "Adding field correspondences")

The `ReadJDBC` component will now generate the `getxxx` method for each column based on the *target* field type - `ReadJDBC` will produce a message if there is a mismatch.

This completes the development stage for this component.  If error messages, or other run-time exceptions, occur, please raise an issue in Issues.

**We now have the "front end" of a generalized FBP-based ETL system.**
