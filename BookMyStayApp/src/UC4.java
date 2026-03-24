import java.util.HashMap;
    public class UC4 {

        static class Room {
            String type;
            double price;

            Room(String type, double price) {
                this.type = type;
                this.price = price;
            }
        }

        public static void main(String[] args) {

            HashMap<String, Integer> inventory = new HashMap<>();
            inventory.put("Single", 10);
            inventory.put("Double", 0);
            inventory.put("Suite", 3);

            HashMap<String, Room> rooms = new HashMap<>();
            rooms.put("Single", new Room("Single", 1000));
            rooms.put("Double", new Room("Double", 1800));
            rooms.put("Suite", new Room("Suite", 3000));

            System.out.println("Available Rooms:");

            for (String type : rooms.keySet()) {

                int available = inventory.get(type);

                if (available > 0) {
                    Room r = rooms.get(type);
                    System.out.println(
                            r.type + " | Price: " + r.price + " | Available: " + available
                    );
                }
            }
        }
    }

