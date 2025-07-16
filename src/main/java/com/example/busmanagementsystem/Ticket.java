package com.example.busmanagementsystem;

import java.util.Date;

public class Ticket {
    private int ticketID;
    private double price;
    private Date issueDate;
    private Reservation reservation;


    public Ticket(int ticketID, double price, Date issueDate, Reservation reservation) {
        this.ticketID = ticketID;
        this.price = price;
        this.issueDate = issueDate;
        this.reservation = reservation;
    }


    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    public void generateTicket() {
        System.out.println("Ticket generated:");
        System.out.println("Ticket ID: " + ticketID);
        System.out.println("Price: " + price);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Reservation Details: ");
        reservation.viewReservation();
    }


    public void viewTicketDetails() {
        System.out.println("Viewing Ticket Details:");
        System.out.println("Ticket ID: " + ticketID);
        System.out.println("Price: " + price);
        System.out.println("Issue Date: " + issueDate);
        if (reservation != null) {
            reservation.viewReservation();
        } else {
            System.out.println("No reservation linked to this ticket.");
        }
    }


    public void cancelTicket() {
        System.out.println("Ticket " + ticketID + " is being canceled.");
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }


    public double calculateRefund() {
        double refund = price * 0.8;
        System.out.println("Refund Amount for Ticket ID " + ticketID + ": " + refund);
        return refund;
    }


    public boolean validateTicket() {
        Date currentDate = new Date();
        boolean isValid = issueDate.before(currentDate);
        System.out.println("Ticket ID " + ticketID + " validation: " + (isValid ? "Valid" : "Invalid"));
        return isValid;
    }
}