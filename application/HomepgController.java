package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomepgController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	

	public void switchToMapmrt(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Mapmrt.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTravelchit(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Travelchit.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToReportfault(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Reportfault.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTimingbus(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BusTimings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCalculatorfare(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BusFare.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPlannertrip(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Plannertrip.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToHomepg(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Homepg.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
