import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TestBusTimings {
    public static void main(String[] args) {
        // Set the API Key
        String apiKey = "x2KGyywxRlCvM6OIfM19UQ==";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the bus stop number: ");
        String busStopNumber = scanner.nextLine();

        CheckBusTimings timingsFetcher = new CheckBusTimings(apiKey);
        try {
            BusTimings timings = timingsFetcher.getBusTimings(busStopNumber);

            if (timings != null) {
                System.out.println("Next Arrival Times for Bus Stop " + busStopNumber + ":");
                for (int i = 0; i < timings.getServiceNumbers().length; i++) {
                    String serviceNo = timings.getServiceNumbers()[i];
                    LocalDateTime arrivalTime = timings.getArrivalTime()[i];
                    String arrivalMins = timings.getArrivalMins()[i];

                    System.out.println("Service: " + serviceNo + "\tNext Arrival Time: " + arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                            + "\tArrival in " + arrivalMins);
                }
            } else {
                System.out.println("Failed to fetch bus timings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







