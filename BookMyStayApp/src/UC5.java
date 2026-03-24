import java.util.LinkedList;
import java.util.Queue;

    class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    public class UC5 {

        public static void main(String[] args) {

            Queue<Reservation> bookingQueue = new LinkedList<>();

            bookingQueue.add(new Reservation("Alice", "Single"));
            bookingQueue.add(new Reservation("Bob", "Double"));
            bookingQueue.add(new Reservation("Charlie", "Suite"));
            bookingQueue.add(new Reservation("David", "Single"));

            System.out.println("Booking Requests in Queue (FIFO Order):\n");

            for (Reservation r : bookingQueue) {
                System.out.println("Guest: " + r.guestName + " | Room Type: " + r.roomType);
            }

        }
    }

