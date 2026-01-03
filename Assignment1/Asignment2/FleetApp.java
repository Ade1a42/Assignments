package Asignment2;

import java.util.*;  // Import everything from util package (Scanner, List, ArrayList)

public class FleetApp {
    // List to store all vehicles
    private List<Vehicle> vehicles = new ArrayList<>();

    private Scanner scanner;

    // CONSTRUCTOR
    public FleetApp() {
        scanner = new Scanner(System.in);
    }

    // MAIN METHOD - program starts here
    public static void main(String[] args) {
        FleetApp app = new FleetApp();
        app.run();
    }

    // MAIN LOOP - runs continuously
    public void run() {
        while (true) {
            printMenu();
            int choice = getIntInput("Enter choice (1-7): ");
            processChoice(choice);
        }
    }

    // Display menu
    private void printMenu() {
        System.out.println("\n=== Fleet Management System ===");
        System.out.println("1. Print all vehicles");
        System.out.println("2. Add new car");
        System.out.println("3. Add new bus");
        System.out.println("4. Show total yearly insurance fees");
        System.out.println("5. Show vehicles older than N years");
        System.out.println("6. Perform service for all vehicles");
        System.out.println("7. Quit");
        System.out.println("================================");
    }

    // Process menu choice
    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                printAllVehicles();
                break;
            case 2:
                addCar();
                break;
            case 3:
                addBus();
                break;
            case 4:
                showTotalInsurance();
                break;
            case 5:
                showOlderThanN();
                break;
            case 6:
                performAllService();
                break;
            case 7:
                quit();
                break;
            default:
                System.out.println("Invalid choice! Please enter 1-7.");
        }
    }

    // Print all vehicles
    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }

        System.out.println("--- All Vehicles in Fleet ---");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    // Add a new car
    private void addCar() {
        System.out.println("--- Add New Car ---");

        // Collect data from user
        String model = getStringInput("Model: ");
        int year = getIntInput("Year: ");
        double price = getDoubleInput("Base price: ");
        int doors = getIntInput("Number of doors (2-6): ");

        try {
            Car car = new Car(model, year, price, doors) {
                @Override
                public double calculateInsuranceFee(int numberOfDoors) {
                    return 0;
                }
            };
            vehicles.add(car);  // Add to list
            System.out.println("Car added successfully! ID: " + car.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Add a new bus
    private void addBus() {
        System.out.println("\n--- Add New Bus ---");

        String model = getStringInput("Model: ");
        int year = getIntInput("Year: ");
        double price = getDoubleInput("Base price: ");
        int capacity = getIntInput("Passenger capacity (10-100): ");

        try {
            Bus bus = new Bus(model, year, price, capacity) {
                @Override
                public double calculateInsuranceFee(int numberOfDoors) {
                    return 0;
                }
            };
            vehicles.add(bus);
            System.out.println("Bus added successfully! ID: " + bus.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Calculate total insurance for all vehicles
    private void showTotalInsurance() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles to calculate insurance");
            return;
        }

        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.calculateInsuranceFee();
        }

        System.out.printf("Total insurance fees for all vehicles: $%.2f\n", total);
    }

    // Find vehicles older than N years
    private void showOlderThanN() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in fleet");
            return;
        }

        int currentYear = getIntInput("Enter current year: ");
        int n = getIntInput("Show vehicles older than how many years? ");

        System.out.println("\n--- Vehicles Older Than " + n + " Years ---");
        boolean found = false;

        for (Vehicle v : vehicles) {
            if (v.getAge(currentYear) > n) {  // Use inherited method
                System.out.println(v);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found.");
        }
    }

    // METHOD 6: Service all vehicles
    private void performAllService() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles to service");
            return;
        }

        System.out.println("\n--- Performing Service for All Vehicles ---");
        for (Vehicle v : vehicles) {
            // POLYMORPHISM: v is Vehicle, but we know it's Serviceable
            // Cast to Serviceable to call service methods
            Servicable.Serviceable s = (Servicable.Serviceable) v;  // Casting: treat Vehicle as Serviceable
            System.out.print("Vehicle " + v.getId() + " (" + v.getModel() + "): ");
            s.performService();  // Calls Car or Bus version!
            System.out.println("   Next service in " + s.getServiceIntervalKm() + " km");
        }
    }

    // Quit program
    private void quit() {
        System.out.println("Thank you for using Fleet Management System!");
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }

    // ============= HELPER METHODS =============

    // Get integer input with validation
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {  // Keep asking until we get an integer
            scanner.next();  // Discard bad input
            System.out.print("Please enter a valid number: ");
        }
        int input = scanner.nextInt();
        scanner.nextLine();  // Consume newline left by nextInt()
        return input;
    }

    // Get double input with validation
    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Please enter a valid number: ");
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    // Get string input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();  // Reads entire line including spaces
    }
}
