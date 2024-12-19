package client;
import javafx.application.Application;

import javafx.stage.Stage;
import java.util.Vector;
import gui.ConnectionFrameController;
import client.ClientController;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance
	public static String ConnectionIP;
	
	public static void main( String args[] ) throws Exception
	   { 
			try {
				ConnectionIP = args[1];
			}
			catch(Exception e) {
				ConnectionIP = "localhost";
			}
			if(ConnectionIP.equals("") || ConnectionIP == null)
				ConnectionIP = "localhost";
		    launch(args); 
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		 chat= new ClientController(ConnectionIP, 5555);
		// TODO Auto-generated method stub
						  		
		 ConnectionFrameController aFrame = new ConnectionFrameController(); // create StudentFrame
		 
		aFrame.start(primaryStage);
	}
	
	
}
