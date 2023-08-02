import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test_BusTimings implements BusTimingsProvider {
    private ApiKeyProvider apiKeyProvider;

    public Test_BusTimings(ApiKeyProvider apiKeyProvider) {
        this.apiKeyProvider = apiKeyProvider;
    }

    @Override
    public BusTimings getBusTimings(String busStopNumber) {
        try {
            String apiKey = apiKeyProvider.getApiKey();

            String apiUrl = "http://datamall2.mytransport.sg/ltaodataservice/BusArrivalv2?BusStopCode=" + busStopNumber;
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("AccountKey", apiKey);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray services = jsonObject.getJSONArray("Services");

                String[] serviceNumbers = new String[services.length()];
                String[] displayTimes = new String[services.length()];
                LocalDateTime[] arrivalTimes = new LocalDateTime[services.length()];

                for (int i = 0; i < services.length(); i++) {
                    JSONObject service = services.getJSONObject(i);
                    String serviceNo = service.getString("ServiceNo");

                    JSONObject nextBus = service.getJSONObject("NextBus");
                    String estimatedArrival = nextBus.getString("EstimatedArrival");

                    // Convert the time to Singapore Time (UTC+8)
                    LocalDateTime arrivalTime = LocalDateTime.parse(estimatedArrival,
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Asia/Singapore")));

                    // Calculate the difference in minutes from now
                    long minutes = Duration.between(LocalDateTime.now(), arrivalTime).toMinutes();

                    String displayTime;
                    if (minutes <= 0) {
                        displayTime = "Arr";
                    } else {
                        displayTime = minutes + " Min";
                    }

                    serviceNumbers[i] = serviceNo;
                    displayTimes[i] = displayTime;
                    arrivalTimes[i] = arrivalTime;
                }

                return new BusTimings(serviceNumbers, displayTimes, arrivalTimes);
            } else {
                System.out.println("Failed to fetch bus timings. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return "NIL" if there are any errors
        return null;
    }
}
