import java.util.*;

    // Reservation class (Confirmed booking)
    class BookingRecord {
        private String reservationId;
        private String guestName;
        private String roomType;

        public BookingRecord(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationId() {
            return reservationId;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    // Booking History (stores confirmed bookings)
    class BookingHistory {
        private List<BookingRecord> history;

        public BookingHistory() {
            history = new ArrayList<>();
        }

        // Add confirmed booking
        public void addBooking(BookingRecord record) {
            history.add(record);
        }

        // Retrieve all bookings (read-only)
        public List<BookingRecord> getAllBookings() {
            return history;
        }
    }

    // Reporting Service
    class BookingReportService {

        // Display full booking history
        public void displayAllBookings(List<BookingRecord> bookings) {
            System.out.println("Booking History:\n");

            for (BookingRecord b : bookings) {
                System.out.println("Reservation ID: " + b.getReservationId());
                System.out.println("Guest: " + b.getGuestName());
                System.out.println("Room Type: " + b.getRoomType());
                System.out.println("--------------------------");
            }
        }

        // Generate summary report
        public void generateSummary(List<BookingRecord> bookings) {
            System.out.println("\nSummary Report:");

            HashMap<String, Integer> roomCount = new HashMap<>();

            for (BookingRecord b : bookings) {
                roomCount.put(b.getRoomType(),
                        roomCount.getOrDefault(b.getRoomType(), 0) + 1);
            }

            for (String type : roomCount.keySet()) {
                System.out.println("Room Type: " + type + " | Bookings: " + roomCount.get(type));
            }
        }
    }

    // Main class
    public class UC8 {

        public static void main(String[] args) {

            // Step 1: Booking History
            BookingHistory history = new BookingHistory();

            // Step 2: Add confirmed bookings (simulate Use Case 6 output)
            history.addBooking(new BookingRecord("RES101", "Alice", "Single"));
            history.addBooking(new BookingRecord("RES102", "Bob", "Double"));
            history.addBooking(new BookingRecord("RES103", "Charlie", "Single"));
            history.addBooking(new BookingRecord("RES104", "David", "Suite"));

            // Step 3: Reporting
            BookingReportService reportService = new BookingReportService();

            reportService.displayAllBookings(history.getAllBookings());
            reportService.generateSummary(history.getAllBookings());
        }

    }