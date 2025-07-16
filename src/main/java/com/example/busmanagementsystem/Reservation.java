package com.example.busmanagementsystem;

import java.util.Date;

public class Reservation {
    private int reservationID;
    private Date reservationDate;
    private int seatNumber;
    private String status;
    private double price;
    private Bus bus;
    private Ticket ticket;
    private Passenger passenger; // Add passenger reference

    // Constructor
    public Reservation(int reservationID, Date reservationDate, int seatNumber, String status, Bus bus, Passenger passenger) {
        this.reservationID = reservationID;
        this.reservationDate = reservationDate;
        this.seatNumber = seatNumber;
        this.status = status;
        this.price = 50.0; // Default price (can be updated)
        this.bus = bus;
        this.passenger = passenger;
        this.ticket = new Ticket(reservationID, price, reservationDate, this);
    }

    // Method to cancel reservation
    public void cancelReservation() {
        this.status = "Canceled";
        System.out.println("Reservation ID " + reservationID + " has been canceled on Bus: " + bus.getRouteDetails());
        if (ticket != null) {
            ticket.cancelTicket();
        }
        bus.freeSeat(seatNumber);
    }

    // Method to make a reservation
    public static Reservation makeReservation(int reservationID, Passenger passenger, Bus bus, int seatNumber) {
        if (bus.isSeatBooked(seatNumber)) {
            System.out.println("Seat " + seatNumber + " is already booked on Bus: " + bus.getRouteDetails());
            return null;
        }

        Reservation reservation = new Reservation(reservationID, new Date(), seatNumber, "Booked", bus, passenger);
        bus.bookSeat(seatNumber);
        System.out.println("Reservation successful for Passenger: " + passenger.getName() +
                " on Bus: " + bus.getRouteDetails() + " Seat: " + seatNumber);
        return reservation;
    }

    // Method to view reservation details
    public String viewReservation() {
        return "Reservation ID: " + reservationID +
                ", Date: " + reservationDate +
                ", Bus: " + bus.getBusNumber() +
                ", Route: " + bus.getRoute() +
                ", Seat Number: " + seatNumber +
                ", Status: " + status +
                ", Price: " + price;
    }


    // Getter and setter methods for Reservation class
    public int getReservationID() {
        return reservationID;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Bus getBus() {
        return bus;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
