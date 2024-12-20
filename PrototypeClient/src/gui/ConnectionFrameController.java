package gui;

import java.io.IOException;


import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public  class ConnectionFrameController   {
	private static int itemIndex = 3;
	
	public String title = "Client Connection";
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private TextField idtxt;
	
	@FXML
	private Button btnConnect;
	
	@FXML
	private TextField serverIPTxt;
	
	@FXML
	private TextField portTxt;
	
	private String getServerIP() {
		return serverIPTxt.getText();
	}
	
	private String getport() {
		return portTxt.getText();
	}
	

	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ConnectionFrame.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/ConnectionFrame.css").toExternalForm());
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		
		primaryStage.show();	 	   
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("Exiting Client from Connection Screen");
		System.exit(0);
	}
	
	public void getConnectBtn(ActionEvent event) throws Exception{
		
		FXMLLoader loader = new FXMLLoader();
		if(this.getServerIP().trim().isEmpty() || this.getport().trim().isEmpty() ) {
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/gui/NoticeFrame.fxml").openStream());
			//ConnectionFrameController connectionFrameController = loader.getController();		
			//connectionFrameController.(ChatClient.s1);
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/gui/NoticeFrame.css").toExternalForm());
			primaryStage.setTitle("Notice");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
		else {
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/gui/SubscriberManagerFrame.fxml").openStream());
			//ConnectionFrameController connectionFrameController = loader.getController();		
			//connectionFrameController.(ChatClient.s1);
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/gui/SubscriberManagerFrame.css").toExternalForm());
			primaryStage.setTitle("Subscriber Management");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
		
		
		
		//subscriber manager
	}
	
	public  void display(String message) {
		System.out.println("message");
		
	}
	
}
