package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SubscriberManagerFrameController {

	@FXML
	private Button btnClose = null;
	
	@FXML
	private Button btnOpen = null;
	
	@FXML
	private Label labelmsg=null;
	
	public void start(Stage primaryStage) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/gui/SubscriberManagerFrame.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/gui/SubscriberManagerFrame.css").toExternalForm());
		primaryStage.setTitle("Subscriber Management");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void getOpenBtn(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/gui/SubscriberInfoFrame.fxml").openStream());
		//ConnectionFrameController connectionFrameController = loader.getController();		
		//connectionFrameController.(ChatClient.s1);
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/gui/SubscriberInfoFrame.css").toExternalForm());
		primaryStage.setTitle("Subscriber Information");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void getCloseButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		((Stage)((Node)event.getSource()).getScene().getWindow()).close(); //close window
		System.exit(0); //close client
	}
	
	
	
	
}
