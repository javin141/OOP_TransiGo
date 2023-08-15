package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BusTimingsController {
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private TextField busStopNumberField;

    @FXML
    private Label resultLabel;

    @FXML
    private Label ServiceNo1, ArrivalTime1, ArrivalMins1;
    @FXML
    private Label ServiceNo2, ArrivalTime2, ArrivalMins2;
    @FXML
    private Label ServiceNo3, ArrivalTime3, ArrivalMins3;
    @FXML
    private Label ServiceNo4, ArrivalTime4, ArrivalMins4;

    @FXML
    private void getBusTimings(ActionEvent event) {
        String busStopNumber = busStopNumberField.getText();

        CheckBusTimings timingsFetcher = new CheckBusTimings();
        try {
            BusTimings timings = timingsFetcher.getBusTimings(busStopNumber);

            if (timings != null) {
                for (int i = 0; i < 4 && i < timings.getServiceNumbers().length; i++) {
                    String serviceNo = timings.getServiceNumbers()[i];
                    LocalDateTime arrivalTime = timings.getArrivalTime()[i];
                    String arrivalMins = timings.getArrivalMins()[i];

                    switch (i) {
                        case 0:
                            ServiceNo1.setText("Service: " + serviceNo);
                            ArrivalTime1.setText(arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                            ArrivalMins1.setText(arrivalMins);
                            break;
                        case 1:
                            ServiceNo2.setText("Service: " + serviceNo);
                            ArrivalTime2.setText(arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                            ArrivalMins2.setText(arrivalMins);
                            break;
                        case 2:
                            ServiceNo3.setText("Service: " + serviceNo);
                            ArrivalTime3.setText(arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                            ArrivalMins3.setText(arrivalMins);
                            break;
                        case 3:
                            ServiceNo4.setText("Service: " + serviceNo);
                            ArrivalTime4.setText(arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                            ArrivalMins4.setText( arrivalMins);
                            break;
                    }
                }
                resultLabel.setText("Next Arrival Times for Bus Stop " + busStopNumber + ":");
            } else {
                resultLabel.setText("Failed to fetch bus timings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
	public void switchToHomepg(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Homepg.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

	