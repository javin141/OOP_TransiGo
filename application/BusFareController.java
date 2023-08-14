package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BusFareController {

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

        adultFareLabel.setText("Adult Fare: " + adultFare);
        studentFareLabel.setText("Student Fare: " + studentFare);
        seniorDisabledFareLabel.setText("Senior/Disabled Fare: " + seniorDisabledFare);
        distanceLabel.setText("Distance between bus stop codes " + startBusStopCode + " and " + endBusStopCode + ": " + distance + " km");
    }
}

