import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

public class TimeConversion {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

        try (Scanner scanner = new Scanner(System.in)) {
            // Step 1: Get user's original time zone
            System.out.print("Enter the original timezone: ");
            String originalZoneId = scanner.nextLine();

            if (!allZoneIds.contains(originalZoneId)) {
                System.out.println("Invalid original timezone: " + originalZoneId);
                return;
            }

            // Step 2: Get user's target time zone
            System.out.print("Enter the target timezone: ");
            String targetZoneId = scanner.nextLine();

            if (!allZoneIds.contains(targetZoneId)) {
                System.out.println("Invalid target timezone: " + targetZoneId);
                return;
            }

            // Step 3: Ask user for the time based on the original time zone
            System.out.printf("Enter the time in %s (format: yyyy-MM-dd HH:mm:ss): ", originalZoneId);
            String timeInput = scanner.nextLine();

            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of(originalZoneId));
                ZonedDateTime originalTime = ZonedDateTime.parse(timeInput, inputFormatter);
                ZonedDateTime convertedTime = originalTime.withZoneSameInstant(ZoneId.of(targetZoneId));
                System.out.printf("Time in %s: %s\n", targetZoneId, convertedTime.format(formatter));
            } catch (Exception ex) {
                System.out.println("Invalid time input. Please ensure you follow the given format.");
            }
        }
    }
}
