package com.example.busmanagementsystem;

public class Vehicle {
    protected int vehicleID;
    protected String licenseNumber;
    protected int capacity;
    protected boolean isRunning;

    // Constructor
    public Vehicle(int vehicleID, String licenseNumber, int capacity) {
        this.vehicleID = vehicleID;
        this.licenseNumber = licenseNumber;
        this.capacity = capacity;
        this.isRunning = false;
    }

    // Get Vehicle Details
    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleID + "\nLicense Number: " + licenseNumber + "\nCapacity: " + capacity + " seats";
    }

    // Start the Vehicle
    public void startVehicle() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Vehicle " + licenseNumber + " has started.");
        } else {
            System.out.println("Vehicle is already running.");
        }
    }

    // Stop the Vehicle
    public void stopVehicle() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Vehicle " + licenseNumber + " has stopped.");
        } else {
            System.out.println("Vehicle is not running.");
        }
    }

    // Update Vehicle Capacity
    public void updateCapacity(int newCapacity) {
        if (newCapacity > 0) {
            capacity = newCapacity;
            System.out.println("Vehicle capacity updated to " + capacity + " seats.");
        } else {
            System.out.println("Invalid capacity. Please enter a positive number.");
        }
    }
}