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

public class Test_BusTimings {
    public static void main(String[] args) {
        // Set the API Key
        String apiKey = "x2KGyywxRlCvM6OIfM19UQ==";

        try {
            // Get the bus stop number from the user
            System.out.print("Enter the bus stop number: ");
            String busStopNumber = new BufferedReader(new InputStreamReader(System.in)).readLine();

            BusTimingsFetcher timingsFetcher = new BusTimingsFetcher(apiKey);
            BusTimings timings = timingsFetcher.getBusTimings(busStopNumber);

            if (timings != null) {
                System.out.println("Next Arrival Times for Bus Stop " + busStopNumber + ":");
                for (int i = 0; i < timings.getServiceNumbers().length; i++) {
                    String serviceNo = timings.getServiceNumbers()[i];
                    String displayTime = timings.getDisplayTimes()[i];

                    System.out.println("Service: " + serviceNo + "\tNext Arrival Time: " + displayTime);
                }
            } else {
                System.out.println("Failed to fetch bus timings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BusTimingsFetcher {
    private String apiKey;

    public BusTimingsFetcher(String apiKey) {
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
            String[] displayTimes = new String[services.length()];

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

                String arrivalTiming = arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"));

                serviceNumbers[i] = serviceNo;
                displayTimes[i] = displayTime + " (" + arrivalTiming + ")";
            }

            return new BusTimings(serviceNumbers, displayTimes);
        } else {
            System.out.println("Failed to fetch bus timings. Response code: " + responseCode);
        }

        return null;
    }
}

class BusTimings {
    private String[] serviceNumbers;
    private String[] displayTimes;

    public BusTimings(String[] serviceNumbers, String[] displayTimes) {
        this.serviceNumbers = serviceNumbers;
        this.displayTimes = displayTimes;
    }

    public String[] getServiceNumbers() {
        return serviceNumbers;
    }

    public String[] getDisplayTimes() {
        return displayTimes;
    }
}

