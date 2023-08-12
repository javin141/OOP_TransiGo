public class CheckBusFare extends DistFinder {

	    public String getAdultFare(String busNo, String startCode, String destCode) {
	        double distance = findDist(busNo, startCode, destCode);
	        int fare = calculateAdultFare(distance);
	        return formatFare(fare);
	    }

	    public String getStudentFare(String busNo, String startCode, String destCode) {
	        double distance = findDist(busNo, startCode, destCode);
	        int fare = calculateStudentFare(distance);
	        return formatFare(fare);
	    }

	    public String getSeniorDisabledFare(String busNo, String startCode, String destCode) {
	        double distance = findDist(busNo, startCode, destCode);
	        int fare = calculateSeniorDisabledFare(distance);
	        return formatFare(fare);
	    }

    public static int calculateAdultFare(double distance) {
        // Define the fare rules for adults based on the given table
        if (distance <= 3.2) {
            return 99;
        } else if (distance <= 4.2) {
            return 109;
        } else if (distance <= 5.2) {
            return 119;
        } else if (distance <= 6.2) {
            return 129;
        } else if (distance <= 7.2) {
            return 138;
        } else if (distance <= 8.2) {
            return 145;
        } else if (distance <= 9.2) {
            return 152;
        } else if (distance <= 10.2) {
            return 156;
        } else if (distance <= 11.2) {
            return 160;
        } else if (distance <= 12.2) {
            return 164;
        } else if (distance <= 13.2) {
            return 168;
        } else if (distance <= 14.2) {
            return 172;
        } else if (distance <= 15.2) {
            return 177;
        } else if (distance <= 16.2) {
            return 181;
        } else if (distance <= 17.2) {
            return 185;
        } else if (distance <= 18.2) {
            return 189;
        } else if (distance <= 19.2) {
            return 193;
        } else if (distance <= 20.2) {
            return 196;
        } else if (distance <= 21.2) {
            return 199;
        } else if (distance <= 22.2) {
            return 202;
        } else if (distance <= 23.2) {
            return 205;
        } else if (distance <= 24.2) {
            return 207;
        } else if (distance <= 25.2) {
            return 209;
        } else if (distance <= 26.2) {
            return 211;
        } else if (distance <= 27.2) {
            return 212;
        } else if (distance <= 28.2) {
            return 213;
        } else if (distance <= 29.2) {
            return 214;
        } else if (distance <= 30.2) {
            return 215;
        } else if (distance <= 31.2) {
            return 216;
        } else if (distance <= 32.2) {
            return 217;
        } else if (distance <= 33.2) {
            return 218;
        } else if (distance <= 34.2) {
            return 219;
        } else if (distance <= 35.2) {
            return 220;
        } else if (distance <= 36.2) {
            return 221;
        } else if (distance <= 37.2) {
            return 222;
        } else if (distance <= 38.2) {
            return 223;
        } else if (distance <= 39.2) {
            return 224;
        } else if (distance <= 40.2) {
            return 224;
        } else {
            return 226; // Over 40.2 km
        }
    }

    public static int calculateStudentFare(double distance) {
        // Define the fare rules for students based on the given table
        if (distance <= 3.2) {
            return 44;
        } else if (distance <= 4.2) {
            return 49;
        } else if (distance <= 5.2) {
            return 54;
        } else if (distance <= 6.2) {
            return 59;
        } else if (distance <= 7.2) {
            return 62;
        } else {
            return 65; // Over 7.2 km
        }
    }

    public static int calculateSeniorDisabledFare(double distance) {
        // Define the fare rules for senior citizens or disabilities based on the given table
        if (distance <= 3.2) {
            return 61;
        } else if (distance <= 4.2) {
            return 68;
        } else if (distance <= 5.2) {
            return 75;
        } else if (distance <= 6.2) {
            return 82;
        } else if (distance <= 7.2) {
            return 88;
        } else {
            return 94; // Over 7.2 km
        }
    }

    public static String formatFare(int fare) {
        // Format the fare as a string in dollars (e.g., "$3.92")
        return "$" + (fare / 100) + "." + (fare % 100);
    }
}
