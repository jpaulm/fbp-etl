package main.java.com.jpaulmorrison.jdbcstuff.networks;

import com.jpaulmorrison.fbp.core.components.io.ReadFile;
import com.jpaulmorrison.fbp.core.components.misc.WriteToConsole;
import com.jpaulmorrison.fbp.core.engine.Network;
import main.java.com.jpaulmorrison.jdbcstuff.core.components.ReadJDBC;

public class Step05 extends Network {

	@Override
	protected void define() throws Exception {
		// TODO Auto-generated method stub
		    connect(component("ReadPswd", ReadFile.class), port("OUT"), 
		    		component("ReadJDBC", ReadJDBC.class), port("PSWD"));
		    connect(component("ReadJDBC"), port("OUT"), component("Print", WriteToConsole.class), port("IN"));


		    initialize("C:/Users/Paul/Documents/jdbc-pswdfile", component("ReadPswd"), port("SOURCE"));
		   
		    	    

		  }

		  public static void main(final String[] argv) throws Exception {
		    new Step05().go();
		  }
	}

 
