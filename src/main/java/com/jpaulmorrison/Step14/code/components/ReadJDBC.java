package com.jpaulmorrison.Step14.code.components;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;

import com.google.gson.Gson;
import com.jpaulmorrison.fbp.core.engine.Component; // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import com.jpaulmorrison.fbp.core.engine.ComponentDescription;
import com.jpaulmorrison.fbp.core.engine.InPort;
import com.jpaulmorrison.fbp.core.engine.InPorts;
import com.jpaulmorrison.fbp.core.engine.InputPort;
import com.jpaulmorrison.fbp.core.engine.OutPort;
import com.jpaulmorrison.fbp.core.engine.OutputPort;
import com.jpaulmorrison.fbp.core.engine.Packet;

@ComponentDescription("Reads table from MySQL and outputs result")
@InPorts({ @InPort(value = "DATABASE", description = "Database name", type = String.class),
		@InPort(value = "USER", description = "User name", type = String.class),
		@InPort(value = "CLASS", description = "Object class", type = String.class),
		@InPort(value = "FIELDS", description = "Field correspondences", type = String.class),
		@InPort(value = "PSWD", description = "Password obtained from file", type = String.class) })
@OutPort(value = "OUT", description = "Table rows")

public class ReadJDBC extends Component {

	// adapted from
	// https://www.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html

	private OutputPort outPort;

	private InputPort pswdPort;
	private InputPort dBNPort;
	private InputPort userPort;
	private InputPort classPort;
	private InputPort fldsPort;

	
	@Override
	protected void execute() throws Exception {

		Packet<?> pp = pswdPort.receive();

		String pswd = (String) pp.getContent();
		drop(pp);
		pswdPort.close();

		pp = dBNPort.receive();

		String dbTable = (String) pp.getContent();
		drop(pp);
		dBNPort.close();

		pp = userPort.receive();

		String user = (String) pp.getContent();
		drop(pp);
		userPort.close();

		pp = classPort.receive();

		String objClass = (String) pp.getContent();
		drop(pp);
		classPort.close();

		String[] iipContents = dbTable.split("!", 2);

		pp = fldsPort.receive();

		String fldsStr = (String) pp.getContent();
		drop(pp);
		fldsPort.close();

		Gson gson = new Gson();
		
		FieldInfo[] fiArray = gson.fromJson(fldsStr, FieldInfo[].class);

		try (

				// Step 1: Allocate a database 'Connection' object
				Connection conn = DriverManager.getConnection(
						// "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
						// "root", pswd); // For MySQL only

						iipContents[0] + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", user, pswd);
				// The format is: "jdbc:mysql://hostname:port/databaseName",
				// "username", "password"

				// Step 2: Allocate a 'Statement' object in the Connection
				Statement stmt = conn.createStatement();) {
			// Step 3: Execute a SQL SELECT query. The query result is returned
			// in a 'ResultSet' object.
			// String strSelect = "select title, author, price, qty from " +
			// iipContents[1];
			String strSelect = "select * from " + iipContents[1];
			System.out.println("The SQL statement is: \"" + strSelect + "\"\n"); // Echo
																					// For
																					// debugging
			// outPort.send(create("The SQL statement is: \"" + strSelect +
			// "\"\n")); // Echo For debugging

			ResultSet rset = stmt.executeQuery(strSelect);

			ResultSetMetaData rsmd;
			int numberOfColumns = 0;
			HashMap<String, String> hmColumns = new HashMap<String, String>();
			try {
				rsmd = rset.getMetaData();
				numberOfColumns = rsmd.getColumnCount();
				for (int i = 1; i <= numberOfColumns; i++) {
					hmColumns.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Step 4: Process the ResultSet by scrolling the cursor forward via
			// next().
			// For each row, retrieve the contents of the cells with
			// getXxx(columnName).
			Class<?> curClass = Class.forName(objClass);

			// number of columns should match number of fields in curClass - so
			// let's test...
			int n = curClass.getFields().length;
			if (n != numberOfColumns) {
				System.out.println(
						"Number of class fields - " + n + " does not match number of columns - " + numberOfColumns);
				return;
			}
			
			HashMap<String, Class<?>> hmFields = new HashMap<String, Class<?>>();
			
			for (Field f: curClass.getFields()) {
				hmFields.put(f.getName(), f.getType());
			}

			// iterate through rows

			int rowCount = 0;
			while (rset.next()) { // Move the cursor to the next row, return
									// false if no more row
				Object obj = null;
				try {
					obj = curClass.newInstance();
				} catch (InstantiationException e) {
					// handle 1
				} catch (IllegalAccessException e) {
					// handle 2
				}
				 

				// iterate through fiArray

				//for (Map.Entry<String, String> entry : hmColumns.entrySet()) {
				for (int i = 0; i < fiArray.length; i++) {
					// System.out.println(entry.getKey() + " = " +
					// entry.getValue());
					String getMethodName = null;

					String colName = fiArray[i].colName;
					String objField = fiArray[i].objField;
									
					System.out.println("JDBC: " + colName + " " + hmColumns.get(colName));
					
					System.out.println("Obj: " + objField + " " + hmFields.get(objField));
					
					String objFType = hmFields.get(objField).toString(); 
					
								
					if (objFType.startsWith("class")) {
						int k = objFType.lastIndexOf(".");
						getMethodName = "get" + objFType.substring(k + 1);
					}
					else {
						getMethodName = "get" + objFType.substring(0, 1).toUpperCase();
						getMethodName += objFType.substring(1).toLowerCase();
					}
					
										
					Class<?>[] cArg = new Class[1];
					cArg[0] = String.class;

					Method meth = null;
					Object o = null;
					try {
						meth = ResultSet.class.getMethod(getMethodName, cArg);					
					    o = meth.invoke(rset, colName);
					
					Field f = curClass.getField(objField);
					f.set(obj, o);
					} catch (InvocationTargetException e) {
						System.out.println("Format mismatch - trying method: '" + 
					    getMethodName + "()' on '" +
					colName + "' " + hmColumns.get(colName) +
								" (target: '" + objField + "' " + hmFields.get(objField) +
								")");
					}
					catch (NoSuchMethodException e) {
						System.out.println("Missing method - trying method: '" + 
					    getMethodName + "()' on '" +
					colName + "' " + hmColumns.get(colName) +
								" (target: '" + objField + "' " + hmFields.get(objField) +
								")");
					}
					
				}

				outPort.send(create(obj));
				++rowCount;
			}

			System.out.println("Total number of records = " + rowCount);
			// outPort.send(create("Total number of records = " + rowCount));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// Step 5: Close conn and stmt - Done automatically by
		// try-with-resources (JDK 7)
	}

	@Override
	protected void openPorts() {
		pswdPort = openInput("PSWD");
		userPort = openInput("USER");
		dBNPort = openInput("DATABASE");
		classPort = openInput("CLASS");
		fldsPort = openInput("FIELDS");
		outPort = openOutput("OUT");
	}

	public class FieldInfo {
		String colName;
		String objField;
	}
}