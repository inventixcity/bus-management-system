package com.example.busmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Bus> buses = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        Admin admin = new Admin(1, "faisal", "1122");

        // Add some buses
        Bus bus1 = new Bus(1, "LHR-123", 50, "Lahore to Islamabad", "B001");
        Bus bus2 = new Bus(2, "KHI-456", 40, "Karachi to Lahore", "B002");
        Bus bus3 = new Bus(3, "OKR-234", 40, "Okara to Lahore", "B003");
        Bus bus4 = new Bus(4, "Qta-456", 78, "Quetta to Lahore", "B004");
        buses.add(bus1);
        buses.add(bus2);
        buses.add(bus3);
        buses.add(bus4);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bus Reservation System");
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. View Buses");
            System.out.println("3. Book a Seat");
            System.out.println("4. Cancel a Reservation");
            System.out.println("5. View All Reservations");
            System.out.println("6. Search Buses by Route");
            System.out.println("7. Admin Report (Total Revenue & Bookings)");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (admin.login(username, password)) {
                        System.out.println("Login successful!");
                        manageBuses(scanner, admin, buses);
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;

                case 2:
                    System.out.println("\nAvailable Buses:");
                    for (Bus bus : buses) {
                        System.out.println(bus.toString());
                    }
                    break;

                case 3:
                    // Book a Seat
                    System.out.print("Enter your name: ");
                    String passengerName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    Passenger passenger = new Passenger(reservations.size() + 1, passengerName, email);

                    System.out.println("Available Buses:");
                    for (int i = 0; i < buses.size(); i++) {
                        System.out.println((i + 1) + ". " + buses.get(i).getRoute());
                    }

                    System.out.print("Select a bus: ");
                    int busIndex = scanner.nextInt() - 1;

                    if (busIndex >= 0 && busIndex < buses.size()) {
                        Bus selectedBus = buses.get(busIndex);
                        System.out.println("Available seats on " + selectedBus.getRoute() + ":");
                        for (int i = 1; i <= selectedBus.getCapacity(); i++) {
                            if (!selectedBus.isSeatBooked(i)) {
                                System.out.println("Seat " + i + " is available.");
                            }
                        }

                        System.out.print("Enter seat number: ");
                        int seatNumber = scanner.nextInt();

                        if (!selectedBus.isSeatBooked(seatNumber)) {
                            // Calculate price
                            double price = 50.0; // Base price
                            if (seatNumber <= 5) {
                                price *= 1.2; // Premium price for front seats
                            }

                            // Apply discount code
                            System.out.print("Enter a discount code (if any): ");
                            scanner.nextLine();  // Consume newline character
                            String discountCode = scanner.nextLine();
                            if (discountCode.equals("SUMMER20")) {
                                price *= 0.8; // 20% discount
                            }

                            System.out.println("Final price after discount: $" + price);

                            Reservation reservation = new Reservation(reservations.size() + 1, new Date(), seatNumber, "Booked", selectedBus, passenger);
                            reservation.setPrice(price);
                            reservations.add(reservation);
                            selectedBus.bookSeat(seatNumber);
                            System.out.println("Reservation successful!");
                        } else {
                            System.out.println("This seat is already booked. Please choose another seat.");
                        }
                    } else {
                        System.out.println("Invalid bus selection.");
                    }
                    break;

                case 4:
                    System.out.print("Enter reservation ID to cancel: ");
                    int reservationID = scanner.nextInt();

                    Reservation reservationToCancel = null;
                    for (Reservation res : reservations) {
                        if (res.getReservationID() == reservationID) {
                            reservationToCancel = res;
                            break;
                        }
                    }

                    if (reservationToCancel != null) {
                        reservationToCancel.cancelReservation();
                        reservations.remove(reservationToCancel);
                        System.out.println("Reservation ID " + reservationID + " has been canceled.");
                    } else {
                        System.out.println("Reservation not found.");
                    }
                    break;

                case 5:
                    admin.viewAllReservations(reservations);
                    break;

                case 6:
                    System.out.print("Enter route to search for: ");
                    String routeSearch = scanner.nextLine();
                    ArrayList<Bus> foundBuses = new ArrayList<>();
                    for (Bus bus : buses) {
                        if (bus.getRoute().toLowerCase().contains(routeSearch.toLowerCase())) {
                            foundBuses.add(bus);
                        }
                    }
                    if (foundBuses.isEmpty()) {
                        System.out.println("No buses found for this route.");
                    } else {
                        System.out.println("Buses found for route: " + routeSearch);
                        for (Bus bus : foundBuses) {
                            System.out.println(bus.toString());
                        }
                    }
                    break;

                case 7:
                    int totalBookings = reservations.size();
                    double totalRevenue = 0;
                    for (Reservation reservation : reservations) {
                        totalRevenue += reservation.getPrice();
                    }
                    System.out.println("Total Bookings: " + totalBookings);
                    System.out.println("Total Revenue: $" + totalRevenue);
                    break;

                case 8:
                    exit = true;
                    System.out.println("Thank you for using the Bus Reservation System!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void manageBuses(Scanner scanner, Admin admin, ArrayList<Bus> buses) {
        boolean adminExit = false;

        while (!adminExit) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a Bus");
            System.out.println("2. Remove a Bus");
            System.out.println("3. Go Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle ID: ");
                    int vehicleID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter license number: ");
                    String licenseNumber = scanner.nextLine();
                    System.out.print("Enter capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter route: ");
                    String route = scanner.nextLine();
                    System.out.print("Enter bus number: ");
                    String busNumber = scanner.nextLine();

                    Bus newBus = new Bus(vehicleID, licenseNumber, capacity, route, busNumber);
                    admin.manageBus(buses, "add", newBus);
                    break;

                case 2:
                    System.out.print("Enter bus number to remove: ");
                    String busNumberToRemove = scanner.nextLine();

                    Bus busToRemove = null;
                    for (Bus bus : buses) {
                        if (bus.getBusNumber().equals(busNumberToRemove)) {
                            busToRemove = bus;
                            break;
                        }
                    }

                    if (busToRemove != null) {
                        admin.manageBus(buses, "remove", busToRemove);
                    } else {
                        System.out.println("Bus not found.");
                    }
                    break;

                case 3:
                    adminExit = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}