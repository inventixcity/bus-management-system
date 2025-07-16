package com.example.busmanagementsystem;

import java.util.ArrayList;

public class Admin {
    private int adminID;
    private String username;
    private String password;

    public Admin(int adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void manageBus(ArrayList<Bus> buses, String action, Bus bus) {
        if (action.equals("add")) {
            if (buses.stream().noneMatch(b -> b.getVehicleID() == bus.getVehicleID())) {
                buses.add(bus);
                System.out.println("Bus added successfully.");
            } else {
                System.out.println("Bus with this Vehicle ID already exists.");
            }
        } else if (action.equals("remove")) {
            if (buses.removeIf(b -> b.getVehicleID() == bus.getVehicleID())) {
                System.out.println("Bus removed successfully.");
            } else {
                System.out.println("Bus not found.");
            }
        }
    }

    public void viewAllReservations(ArrayList<Reservation> reservations) {
        System.out.println("\nAll Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getReservationID() + 
                               ", Bus: " + reservation.getBus().getBusNumber() + 
                               ", Seat Number: " + reservation.getSeatNumber() + 
                               ", Price: " + reservation.getPrice() + 
                               ", Date: " + reservation.getReservationDate());
        }
    }

    public ArrayList<Bus> getAllBuses(ArrayList<Bus> buses) {
        return new ArrayList<>(buses);
    }

    // Getters
    public int getAdminID() {
        return adminID;
    }

    public String getUsername() {
        return username;
    }
}