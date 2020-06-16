FBP-ETL
=======

#### Componentizing ReadJDBC.java (3)


## "Componentizing" ReadJDBC.java (continued)   
     

In the previous step, we said in connection with the code in https://github.com/jpaulm/fbp-etl/blob/master/src/com/jpaulmorrison/Step12/code/components/ReadJDBC.java that

- there are no table column names or IP object (`Book`) field names hard-wired in the code
- it assumes that the table column names and object field names *are identical*
- what is (currently) hard-wired is the correspondence between `VARCHAR` and `String`, `DECIMAL` and `BigDecimal`, etc.

We are going to try to relax these constraints.

First, we are going to add a JSON file of correspondences between table columns and `Book` fields.  This will be passed to `ReadJDBC` in a separate input port.  The IP conetnts will look like this:

```
[{"colName": "id", 
  "objField": "id"},
  {"colName": "title", 
  "objField": "title"},
  {"colName": "author", 
  "objField": "author"},
  {"colName": "price", 
  "objField": "price"},
  {"colName": "qty", 
  "objField": "qty"}
]

```

For convenience, we will just put this in an IIP - as far as `ReadJDBC` is concerned, it could be in an IIP, or on a file - it doesn't care.