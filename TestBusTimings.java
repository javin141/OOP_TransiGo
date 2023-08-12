import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TestBusTimings {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        for (;;) {
        System.out.print("Enter the bus stop number: ");
        String busStopNumber = scanner.nextLine();

        CheckBusTimings timingsFetcher = new CheckBusTimings();
        try {
            BusTimings timings = timingsFetcher.getBusTimings(busStopNumber);

            if (timings != null) {
                System.out.println("Next Arrival Times for Bus Stop " + busStopNumber + ":");
                for (int i = 0; i < timings.getServiceNumbers().length; i++) {
                    String serviceNo = timings.getServiceNumbers()[i];
                    LocalDateTime arrivalTime = timings.getArrivalTime()[i];
                    String arrivalMins = timings.getArrivalMins()[i];

                    System.out.println("Service: " + serviceNo + "  Next Arrival Time: " + arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                            + "  Arrival in " + arrivalMins);
                }
            } else {
                System.out.println("Failed to fetch bus timings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	}
    }
}
