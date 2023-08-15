package application;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestBusFare {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter the bus number: ");
            String busNumber = reader.readLine();

            System.out.print("Enter the start bus stop code: ");
            String startBusStopCode = reader.readLine();

            System.out.print("Enter the end bus stop code: ");
            String endBusStopCode = reader.readLine();

            DistFinder distFinder = new DistFinder();
            CheckBusFare fareChecker = new CheckBusFare();
            
            // Calculate the distance using DistFinder
            double distance = distFinder.findDist(busNumber, startBusStopCode, endBusStopCode);
            
            // Get the bus fares
            String adultFare = fareChecker.getAdultFare(busNumber, startBusStopCode, endBusStopCode);
            String studentFare = fareChecker.getStudentFare(busNumber, startBusStopCode, endBusStopCode);
            String seniorDisabledFare = fareChecker.getSeniorDisabledFare(busNumber, startBusStopCode, endBusStopCode);



            System.out.println("Adult Fare: " + adultFare);
            System.out.println("Student Fare: " + studentFare);
            System.out.println("Senior/Disabled Fare: " + seniorDisabledFare);
            System.out.println("Distance between bus stop codes " +
                    startBusStopCode + " and " + endBusStopCode + ": " + distance + " km");

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}