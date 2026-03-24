import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator class
class BookingValidator {

    // Validate booking input
    public static void validate(String roomType, Map<String, Integer> inventory)
            throws InvalidBookingException {

        // Check if room type exists
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Check availability
        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

// Main class
public class UC9 {

    public static void main(String[] args) {

        // Inventory setup
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);

        // Test booking inputs (some invalid)
        String[] requests = {"Single", "Suite", "Double", "Single", "Single"};

        for (String roomType : requests) {

            try {
                System.out.println("\nProcessing booking for: " + roomType);

                // 🔹 Validate BEFORE processing
                BookingValidator.validate(roomType, inventory);

                // 🔹 If valid → proceed
                int current = inventory.get(roomType);
                inventory.put(roomType, current - 1);

                System.out.println("Booking successful for " + roomType);

            } catch (InvalidBookingException e) {

                // 🔹 Graceful error handling
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        // Final state
        System.out.println("\nFinal Inventory: " + inventory);
    }
}
