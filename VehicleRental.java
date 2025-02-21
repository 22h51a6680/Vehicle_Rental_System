
import java.util.Scanner;

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

    public abstract void displayDetails();
}

class Car extends Vehicle {

    public Car() {
        super("Car", 2000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Car - Rs. 2000/day");
    }
}

class Bike extends Vehicle {

    public Bike() {
        super("Bike", 500);
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike - Rs. 500/day");
    }
}

class Reservation {

    private final Vehicle vehicle;
    private final int days;

    public Reservation(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }

    public double calculateTotalPrice() {
        return vehicle.getPricePerDay() * days;
    }

    public String getDetails() {
        return "Vehicle: " + vehicle.getType() + "\nDays: " + days + "\nTotal Price: " + calculateTotalPrice() + " Rs";
    }
}

public class VehicleRental {

    private static void displayAvailableVehicles() {
        System.out.println("Available Vehicles:");
        new Car().displayDetails();
        new Bike().displayDetails();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(" Welcome to Vehicle Rental System ");
            System.out.println("------------------------------------");
            displayAvailableVehicles();

            System.out.print("Enter the vehicle type ([1] for Car, [2] for Bike): ");
            int vehicleChoice = scanner.nextInt();

            Vehicle vehicle = switch (vehicleChoice) {
                case 1 ->
                    new Car();
                case 2 ->
                    new Bike();
                default ->
                    null;
            };

            if (vehicle == null) {
                System.out.println(" Invalid choice. Please restart and enter a valid option.");
                return;
            }

            System.out.print("Enter the number of days: ");
            int days = scanner.nextInt();

            if (days <= 0) {
                System.out.println(" Invalid number of days. Please enter a positive value.");
                return;
            }

            Reservation reservation = new Reservation(vehicle, days);
            System.out.println("\n Reservation Successful! Here are your details:");
            System.out.println(reservation.getDetails());
        } catch (Exception e) {
            System.out.println(" Error: Invalid input. Please restart and enter valid details.");
        }
    }
}
