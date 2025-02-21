
import java.util.InputMismatchException;
import java.util.Scanner;

// 🔹 Abstract Class for Vehicles (Abstraction)
abstract class Vehicle {

    private final String type;
    private final double pricePerDay;

    public Vehicle(String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public String getType() {
        return type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // 🔹 Abstract method for additional vehicle info (Polymorphism)
    public abstract String getVehicleInfo();
}

// 🔹 Car Class (Inheritance)
class Car extends Vehicle {

    public Car() {
        super("Car", 2000);
    }

    @Override
    public String getVehicleInfo() {
        return "A car is a four-wheeled vehicle for comfortable travel.";
    }
}

// 🔹 Bike Class (Inheritance)
class Bike extends Vehicle {

    public Bike() {
        super("Bike", 500);
    }

    @Override
    public String getVehicleInfo() {
        return "A bike is a two-wheeled vehicle for quick and efficient travel.";
    }
}

// 🔹 Reservation Class (Encapsulation)
class Reservation {

    private final Vehicle vehicle;
    private final int days;

    public Reservation(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }

    // 🔹 Calculate Total Price (Polymorphism)
    public double calculateTotalPrice() {
        return vehicle.getPricePerDay() * days;
    }

    public String getDetails() {
        return "\n🚗 Vehicle: " + vehicle.getType()
                + "\n📅 Days: " + days
                + "\n💰 Total Price: " + calculateTotalPrice() + " Rs"
                + "\nℹ️ " + vehicle.getVehicleInfo();
    }
}

// 🔹 Main Rental System Class
public class VehicleRental {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = null;
        int days = 0;

        try {
            System.out.println("🚘 Welcome to Vehicle Rental System 🚘");
            System.out.println("--------------------------------------");
            System.out.println("Available vehicles:");
            System.out.println("1️⃣ Car - 2000 Rs/day");
            System.out.println("2️⃣ Bike - 500 Rs/day");
            System.out.print("Enter your choice ([1] for Car, [2] for Bike): ");

            int vehicleChoice = scanner.nextInt();
            vehicle = getVehicle(vehicleChoice);

            if (vehicle == null) {
                throw new IllegalArgumentException("Invalid vehicle selection!");
            }

            System.out.print("Enter the number of days: ");
            days = scanner.nextInt();

            if (days <= 0) {
                throw new IllegalArgumentException("Days must be greater than 0!");
            }

            // 🔹 Creating Reservation
            Reservation reservation = new Reservation(vehicle, days);
            System.out.println("\n✅ Reservation Confirmed:");
            System.out.println(reservation.getDetails());

        } catch (InputMismatchException e) {
            System.out.println("❌ Error: Please enter a valid number!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // 🔹 Get Vehicle based on user choice (Encapsulation)
    private static Vehicle getVehicle(int choice) {
        return switch (choice) {
            case 1 ->
                new Car();
            case 2 ->
                new Bike();
            default ->
                null;
        };
    }
}
