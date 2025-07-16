package com.example.busmanagementsystem;

import java.util.Date;

class Payment {
    private int paymentID;
    private double paymentAmount;
    private Date paymentDate;

    public Payment(int paymentID, double paymentAmount, Date paymentDate) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
//Method to process the Payment

    public void processPayment() {
        try {
            if (paymentAmount <= 0) {
                throw new IllegalArgumentException("Payment amount must be greater than zero.");
            }
            System.out.println("Payment of $" + paymentAmount + " processed successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }

    //Method to handle refunds

    public void refundPayment() {
        try {

            System.out.println("Payment of $" + paymentAmount + " refunded successfully.");
        } catch (Exception e) {
            System.err.println("Error refunding payment: " + e.getMessage());
        }
    }

    public void generateInvoice() {
        try {

            System.out.println("Invoice generated for payment ID: " + paymentID);
        } catch (Exception e) {
            System.err.println("Error generating invoice: " + e.getMessage());
        }
    }
}
