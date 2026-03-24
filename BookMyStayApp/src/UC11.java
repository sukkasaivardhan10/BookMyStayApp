import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking Processor (Thread-Safe)
class BookingProcessor {

    private Map<String, Integer> inventory;

    public BookingProcessor(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    // 🔒 Critical Section (synchronized)
    public synchronized void processBooking(BookingRequest request) {

        int available = inventory.getOrDefault(request.roomType, 0);

        if (available > 0) {
            // Simulate delay (to show race condition prevention)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inventory.put(request.roomType, available - 1);

            System.out.println(Thread.currentThread().getName()
                    + " → Booking SUCCESS for " + request.guestName
                    + " (" + request.roomType + ")");
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " → Booking FAILED for " + request.guestName
                    + " (" + request.roomType + ")");
        }
    }
}

// Worker Thread
class BookingThread extends Thread {

    private BookingProcessor processor;
    private BookingRequest request;

    public BookingThread(BookingProcessor processor, BookingRequest request) {
        this.processor = processor;
        this.request = request;
    }

    public void run() {
        processor.processBooking(request);
    }
}

// Main class
public class UC11 {

    public static void main(String[] args) {

        // 🔹 Shared Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2); // limited rooms

        // 🔹 Shared Processor
        BookingProcessor processor = new BookingProcessor(inventory);

        // 🔹 Multiple concurrent requests
        BookingThread t1 = new BookingThread(processor, new BookingRequest("Alice", "Single"));
        BookingThread t2 = new BookingThread(processor, new BookingRequest("Bob", "Single"));
        BookingThread t3 = new BookingThread(processor, new BookingRequest("Charlie", "Single"));

        // 🔹 Start threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();

        // 🔹 Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 🔹 Final Inventory
        System.out.println("\nFinal Inventory: " + inventory);
    }
}