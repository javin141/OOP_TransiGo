package application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class DistFinder {
    private static final String apiUrl = "http://datamall2.mytransport.sg/ltaodataservice/BusRoutes";
    private String apiKey;

    public DistFinder(){
    APIKeyProvider apiKeyProvider = new API();
	this.apiKey = apiKeyProvider.getAPIKey();
    }

    public double findDist(String busNumber, String startBusStopCode, String endBusStopCode) {
        try {
            int currentPage = 0;
            double startDistance = -1;
            double endDistance = -1;

            while (true) {
                URL url = new URL(apiUrl + "?$skip=" + currentPage * 500);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("AccountKey", apiKey);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray busRoutes = jsonResponse.getJSONArray("value");

                if (busRoutes.length() == 0) {
                    break; // No more data to fetch
                }

                for (int i = 0; i < busRoutes.length(); i++) {
                    JSONObject route = busRoutes.getJSONObject(i);
                    if (route.getString("ServiceNo").equals(busNumber)) {
                        if (route.getString("BusStopCode").equals(startBusStopCode)) {
                            startDistance = route.getDouble("Distance");
                        } else if (route.getString("BusStopCode").equals(endBusStopCode)) {
                            endDistance = route.getDouble("Distance");
                        }

                        if (startDistance != -1 && endDistance != -1) {
                            return Math.abs(startDistance - endDistance);
                        }
                    }
                }

                currentPage++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}