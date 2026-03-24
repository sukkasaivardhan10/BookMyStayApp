import java.util.*;

// Booking Record (confirmed booking)
class Booking {
    String reservationId;
    String roomType;
    String roomId;

    Booking(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Booking> bookingMap;   // Active bookings
    private Map<String, Integer> inventory;    // Inventory
    private Stack<String> rollbackStack;       // Released room IDs

    public CancellationService(Map<String, Booking> bookingMap,
                               Map<String, Integer> inventory) {
        this.bookingMap = bookingMap;
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        // 🔹 Validate booking exists
        if (!bookingMap.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID -> " + reservationId);
            return;
        }

        Booking booking = bookingMap.get(reservationId);

        // 🔹 Push room ID to rollback stack (LIFO)
        rollbackStack.push(booking.roomId);

        // 🔹 Restore inventory
        int current = inventory.getOrDefault(booking.roomType, 0);
        inventory.put(booking.roomType, current + 1);

        // 🔹 Remove booking (mark as cancelled)
        bookingMap.remove(reservationId);

        // 🔹 Confirmation
        System.out.println("Booking Cancelled Successfully:");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Released Room ID: " + booking.roomId);
        System.out.println("---------------------------");
    }

    // Show rollback history
    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recently Released Room IDs): " + rollbackStack);
    }
}

// Main class
public class UC10 {

    public static void main(String[] args) {

        // 🔹 Inventory setup
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);

        // 🔹 Existing confirmed bookings
        Map<String, Booking> bookingMap = new HashMap<>();
        bookingMap.put("RES101", new Booking("RES101", "Single", "Single-101"));
        bookingMap.put("RES102", new Booking("RES102", "Double", "Double-201"));

        // 🔹 Cancellation Service
        CancellationService service = new CancellationService(bookingMap, inventory);

        // 🔹 Perform cancellations
        service.cancelBooking("RES101");   // valid
        service.cancelBooking("RES999");   // invalid
        service.cancelBooking("RES102");   // valid

        // 🔹 Final state
        System.out.println("\nFinal Inventory: " + inventory);

        service.showRollbackStack();
    }
}
