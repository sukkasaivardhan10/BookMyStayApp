import java.util.*;

    class BookingRequest {
        String guestName;
        String roomType;

        BookingRequest(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    public class UC6{

        public static void main(String[] args) {

            HashMap<String, Integer> inventory = new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 1);
            inventory.put("Suite", 1);

            Queue<BookingRequest> queue = new LinkedList<>();
            queue.add(new BookingRequest("Alice", "Single"));
            queue.add(new BookingRequest("Bob", "Single"));
            queue.add(new BookingRequest("Charlie", "Single"));
            queue.add(new BookingRequest("David", "Suite"));

            Set<String> allocatedRoomIds = new HashSet<>();
            HashMap<String, Set<String>> allocationMap = new HashMap<>();

            System.out.println("Processing Booking Requests...\n");

            while (!queue.isEmpty()) {

                BookingRequest r = queue.poll();

                int available = inventory.getOrDefault(r.roomType, 0);

                if (available > 0) {

                    String roomId;
                    do {
                        roomId = r.roomType + "-" + (int)(Math.random() * 1000);
                    } while (allocatedRoomIds.contains(roomId));

                    allocatedRoomIds.add(roomId);

                    allocationMap.putIfAbsent(r.roomType, new HashSet<>());
                    allocationMap.get(r.roomType).add(roomId);

                    inventory.put(r.roomType, available - 1);

                    System.out.println("Booking Confirmed:");
                    System.out.println("Guest: " + r.guestName);
                    System.out.println("Room Type: " + r.roomType);
                    System.out.println("Room ID: " + roomId);
                    System.out.println("------------------");

                } else {
                    System.out.println("Booking Failed:");
                    System.out.println("Guest: " + r.guestName);
                    System.out.println("Room Type: " + r.roomType);
                    System.out.println("------------------");
                }
            }
        }
    }

