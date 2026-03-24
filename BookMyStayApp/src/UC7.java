import java.util.*;

    class AddOnService {
        private String name;
        private double cost;

        public AddOnService(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }
    }

    // Add-On Service Manager
    class AddOnServiceManager {

        // Map<ReservationID, List of Services>
        private HashMap<String, List<AddOnService>> serviceMap;

        public AddOnServiceManager() {
            serviceMap = new HashMap<>();
        }

        // Add service to a reservation
        public void addService(String reservationId, AddOnService service) {
            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);
        }

        // Display services + calculate total cost
        public void displayServices(String reservationId) {

            if (!serviceMap.containsKey(reservationId)) {
                System.out.println("No services selected for " + reservationId);
                return;
            }

            double totalCost = 0;

            System.out.println("Reservation ID: " + reservationId);

            for (AddOnService s : serviceMap.get(reservationId)) {
                System.out.println("Service: " + s.getName() + " | Cost: " + s.getCost());
                totalCost += s.getCost();
            }

            System.out.println("Total Add-On Cost: " + totalCost);
            System.out.println("--------------------------");
        }
    }

    // Main class
    public class UC7 {

        public static void main(String[] args) {

            // Existing reservations (from Use Case 6)
            String res1 = "RES101";
            String res2 = "RES102";

            // Create services
            AddOnService breakfast = new AddOnService("Breakfast", 200);
            AddOnService wifi = new AddOnService("WiFi", 100);
            AddOnService spa = new AddOnService("Spa", 500);

            // Service Manager
            AddOnServiceManager manager = new AddOnServiceManager();

            // Guest selects services
            manager.addService(res1, breakfast);
            manager.addService(res1, wifi);
            manager.addService(res2, spa);

            // Display results
            System.out.println("Add-On Services for Reservations:\n");

            manager.displayServices(res1);
            manager.displayServices(res2);
        }
    }


