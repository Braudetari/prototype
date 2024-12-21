package server;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import gui.ServerPortFrameController;
import server.BLibServer;

public class ServerUI extends Application {
	final public static int DEFAULT_PORT = 5555;
	static BLibServer server;
	
	public static void main( String args[] ) throws Exception
	   {   
		 launch(args);
	  } // end main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub				  		
		ServerPortFrameController ServerPortFrame = new ServerPortFrameController(); // create StudentFrame
		ServerPortFrame.start(primaryStage);
	}
	
	public static BLibServer runServer(String p)
	{
		 int port = 0; //Port to listen on

	        try
	        {
	        	port = Integer.parseInt(p); //Set port	          
	        }
	        catch(Throwable t)
	        {
	        	System.out.println("ERROR - Could not connect!");
	        }
	    	
	        server = new BLibServer(port);
	        try {
		        server.dbConnection = DatabaseConnection.connectToDB();
	        }
	        catch(Exception e) {
	        	System.out.println("ERROR - could not connect to DB");
	        	stopServer();
	        	return null;
	        }
	        try 
	        {
	          server.listen(); //Start listening for connections
	        } 
	        catch (Exception ex) 
	        {
	          System.out.println("ERROR - Could not listen for clients!");
	          stopServer();
	          return null;
	        }
	        return server;
	}
	
	public static void stopServer() {
		server.stopListening();
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("ERROR - could not close server");
		}
		System.exit(0);
	}
	

}
