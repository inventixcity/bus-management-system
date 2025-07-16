package com.example.busmanagementsystem;

public class Passenger {
    // Attributes
    private int passengerID;
    private String name;
    private String contactNumber;

    // Constructor
    public Passenger(int passengerID, String name, String contactNumber) {
        this.passengerID = passengerID;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // Methods
    public void bookTicket() {
        // Logic to book a ticket for the passenger
        System.out.println("Ticket booked for " + name);
    }

    public void cancelTicket() {
        // Logic to cancel the passenger's ticket
        System.out.println("Ticket canceled for " + name);
    }

    public String getPassengerDetails() {
        // Returns the passenger's details
        return "Passenger ID: " + passengerID + ", Name: " + name + ", Contact: " + contactNumber;
    }

    // Getters and Setters (if required)
    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}