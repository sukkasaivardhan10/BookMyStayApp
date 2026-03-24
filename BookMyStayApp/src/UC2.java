
    abstract class Room {

        protected int beds;
        protected int size;
        protected double price;

        public Room(int beds, int size, double price) {
            this.beds = beds;
            this.size = size;
            this.price = price;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + beds);
            System.out.println("Room Size: " + size + " sq ft");
            System.out.println("Price per night: ₹" + price);
        }
    }

    class SingleRoom extends Room {

        public SingleRoom() {
            super(1, 200, 1000);
        }
    }

    class DoubleRoom extends Room {

        public DoubleRoom() {
            super(2, 350, 1800);
        }
    }

    class SuiteRoom extends Room {

        public SuiteRoom() {
            super(3, 500, 3500);
        }
    }

    public class UC2 {

        public static void main(String[] args) {

            System.out.println("===== Book My Stay App =====");
            System.out.println("Hotel Booking Management System");
            System.out.println("Version 2.1\n");

            Room single = new SingleRoom();
            Room doub = new DoubleRoom();
            Room suite = new SuiteRoom();

            int singleAvailable = 5;
            int doubleAvailable = 3;
            int suiteAvailable = 2;

            System.out.println("Single Room Details:");
            single.displayRoomDetails();
            System.out.println("Available Rooms: " + singleAvailable + "\n");

            System.out.println("Double Room Details:");
            doub.displayRoomDetails();
            System.out.println("Available Rooms: " + doubleAvailable + "\n");

            System.out.println("Suite Room Details:");
            suite.displayRoomDetails();
            System.out.println("Available Rooms: " + suiteAvailable);
        }
    }
