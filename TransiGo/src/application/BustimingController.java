package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;


public class BustimingController {
	
	@FXML
	private Label myLabel;
	@FXML
	private TextField myTextField;
	@FXML
	private Button myButton;
	
	int busstopcode;
	
	public void submit(ActionEvent event) {
	
		try {
		busstopcode = Integer.parseInt(myTextField.getText());
		System.out.println(busstopcode);
		}
		catch (Exception e){
			System.out.println();
		}
}
	
}