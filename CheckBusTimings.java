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

public class CheckBusTimings {
    private String apiKey;

    public CheckBusTimings(String apiKey) {
        this.apiKey = apiKey;
    }

    public BusTimings getBusTimings(String busStopNumber) throws IOException {
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
            LocalDateTime[] arrivalTime = new LocalDateTime[services.length()];
            String[] arrivalMins = new String[services.length()];

            for (int i = 0; i < services.length(); i++) {
                JSONObject service = services.getJSONObject(i);
                String serviceNo = service.getString("ServiceNo");

                JSONObject nextBus = service.getJSONObject("NextBus");
                String estimatedArrival = nextBus.getString("EstimatedArrival");

                // Convert the time to Singapore Time (UTC+8)
                LocalDateTime arrivalDateTime = LocalDateTime.parse(estimatedArrival,
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Asia/Singapore")));

                // Calculate the difference in minutes from now
                long minutes = Duration.between(LocalDateTime.now(), arrivalDateTime).toMinutes();

                serviceNumbers[i] = serviceNo;
                arrivalTime[i] = arrivalDateTime;
                arrivalMins[i] = minutes <= 0 ? "Arr" : minutes + " Min";
            }

            return new BusTimings(serviceNumbers, arrivalTime, arrivalMins);
        } else {
            System.out.println("Failed to fetch bus timings. Response code: " + responseCode);
        }

        return null;
    }
}

class BusTimings {
    private String[] serviceNumbers;
    private LocalDateTime[] arrivalTime;
    private String[] arrivalMins;

    public BusTimings(String[] serviceNumbers, LocalDateTime[] arrivalTime, String[] arrivalMins) {
        this.serviceNumbers = serviceNumbers;
        this.arrivalTime = arrivalTime;
        this.arrivalMins = arrivalMins;
    }

    public String[] getServiceNumbers() {
        return serviceNumbers;
    }

    public LocalDateTime[] getArrivalTime() {
        return arrivalTime;
    }

    public String[] getArrivalMins() {
        return arrivalMins;
    }
}

