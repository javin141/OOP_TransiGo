package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField busStopNumberField;

    @FXML
    private Label resultLabel;

    @FXML
    private void getBusTimings(ActionEvent event) {
        String busStopNumber = busStopNumberField.getText();

        CheckBusTimings timingsFetcher = new CheckBusTimings();
        try {
            BusTimings timings = timingsFetcher.getBusTimings(busStopNumber);

            if (timings != null) {
                StringBuilder resultText = new StringBuilder("Next Arrival Times for Bus Stop " + busStopNumber + ":\n");
                for (int i = 0; i < timings.getServiceNumbers().length; i++) {
                    String serviceNo = timings.getServiceNumbers()[i];
                    LocalDateTime arrivalTime = timings.getArrivalTime()[i];
                    String arrivalMins = timings.getArrivalMins()[i];

                    resultText.append("Service: ")
                              .append(serviceNo)
                              .append("  Next Arrival Time: ")
                              .append(arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                              .append("  Arrival in ")
                              .append(arrivalMins)
                              .append("\n");
                }
                resultLabel.setText(resultText.toString());
            } else {
                resultLabel.setText("Failed to fetch bus timings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
}


	