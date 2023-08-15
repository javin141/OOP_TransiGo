package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BusFareController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TextField busNumberField;

    @FXML
    private TextField startBusStopField;

    @FXML
    private TextField endBusStopField;

    @FXML
    private Label adultFareLabel;

    @FXML
    private Label studentFareLabel;

    @FXML
    private Label seniorDisabledFareLabel;

    @FXML
    private Label distanceLabel;

    @FXML
    private void calculateFaresAndDistance() {
        String busNumber = busNumberField.getText();
        String startBusStopCode = startBusStopField.getText();
        String endBusStopCode = endBusStopField.getText();

        DistFinder distFinder = new DistFinder();
        CheckBusFare fareChecker = new CheckBusFare();

        double distance = distFinder.findDist(busNumber, startBusStopCode, endBusStopCode);
        String adultFare = fareChecker.getAdultFare(busNumber, startBusStopCode, endBusStopCode);
        String studentFare = fareChecker.getStudentFare(busNumber, startBusStopCode, endBusStopCode);
        String seniorDisabledFare = fareChecker.getSeniorDisabledFare(busNumber, startBusStopCode, endBusStopCode);

        adultFareLabel.setText(adultFare);
        studentFareLabel.setText(studentFare);
        seniorDisabledFareLabel.setText(seniorDisabledFare);
        distanceLabel.setText(distance + " km");
    }
	public void switchToHomepg(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Homepg.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

