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

public class SubscriberInfoFrameController {

	@FXML
	private Button btnClose = null;
	
	@FXML
	private Button btnUpdate = null;
	
	@FXML
	private Label labelmsg=null;
	
	
	public void getUpdateBtn(ActionEvent event) throws Exception{
		
	}
	
	public void getCloseButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
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
	
	
	
	
}
