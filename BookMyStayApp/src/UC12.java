import java.io.*;
import java.util.*;

// Renamed class to avoid duplicate
class PersistentBookingRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    String reservationId;
    String guestName;
    String roomType;

    PersistentBookingRecord(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// System state wrapper
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<PersistentBookingRecord> bookings;

    SystemState(Map<String, Integer> inventory,
                List<PersistentBookingRecord> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    public static void save(SystemState state) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state.");
        }
    }

    public static SystemState load() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            SystemState state = (SystemState) in.readObject();
            System.out.println("System state loaded successfully.");
            return state;

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        return new SystemState(new HashMap<>(), new ArrayList<>());
    }
}

// Main class
public class UC12 {

    public static void main(String[] args) {

        SystemState state = PersistenceService.load();

        Map<String, Integer> inventory = state.inventory;
        List<PersistentBookingRecord> bookings = state.bookings;

        if (inventory.isEmpty()) {
            inventory.put("Single", 2);
            inventory.put("Double", 1);

            bookings.add(new PersistentBookingRecord("RES101", "Alice", "Single"));
            bookings.add(new PersistentBookingRecord("RES102", "Bob", "Double"));
        }

        System.out.println("\nInventory: " + inventory);

        System.out.println("\nBookings:");
        for (PersistentBookingRecord b : bookings) {
            System.out.println(b.reservationId + " | " + b.guestName + " | " + b.roomType);
        }

        // Add new booking
        bookings.add(new PersistentBookingRecord("RES103", "Charlie", "Single"));
        inventory.put("Single", inventory.get("Single") - 1);

        PersistenceService.save(new SystemState(inventory, bookings));
    }
}