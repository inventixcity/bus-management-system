package com.example.busmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private int vehicleID;
    private String licenseNumber;
    private int capacity;
    private String route;
    private String busNumber;
    private List<Seat> seats;

    public Bus(int vehicleID, String licenseNumber, int capacity, String route, String busNumber) {
        this.vehicleID = vehicleID;
        this.licenseNumber = licenseNumber;
        this.capacity = capacity;
        this.route = route;
        this.busNumber = busNumber;
        this.seats = new ArrayList<>();

        // Initialize seats for the bus
        for (int i = 1; i <= capacity; i++) {
            seats.add(new Seat(i));
        }
    }

    public String getRoute() {
        return route;
    }

    public String getRouteDetails() {
        return "Route: " + route + ", Bus Number: " + busNumber;
    }

    public boolean isSeatBooked(int seatNumber) {
        if (seatNumber < 1 || seatNumber > capacity) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        return seats.get(seatNumber - 1).isBooked();
    }

    public void freeSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > capacity) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        seats.get(seatNumber - 1).freeSeat();
        System.out.println("Seat " + seatNumber + " on Bus " + busNumber + " has been freed.");
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > capacity) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        if (seats.get(seatNumber - 1).isBooked()) {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked");
        }
        seats.get(seatNumber - 1).bookSeat();
        System.out.println("Seat " + seatNumber + " on Bus " + busNumber + " has been booked.");
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void displayStatus() {
        System.out.println("Bus Number: " + busNumber);
        System.out.println("Route: " + route);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Seats: " + getAvailableSeats().size());
    }

    // Additional getters
    public int getVehicleID() {
        return vehicleID;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}