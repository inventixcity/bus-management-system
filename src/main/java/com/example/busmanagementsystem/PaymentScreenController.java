package com.example.busmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentScreenController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expiryDateField;

    @FXML
    private TextField cvvField;

    @FXML
    private Label totalAmountLabel;

    private double totalAmount;
    private boolean paymentSuccessful = false;

    public void setTotalAmount(double amount) {
        this.totalAmount = amount;
        totalAmountLabel.setText(String.format("Total Amount: $%.2f", amount));
    }

    @FXML
    private void handleMakePayment() {
        // In a real application, you would process the payment here
        // For this dummy implementation, we'll just check if all fields are filled
        if (!cardNumberField.getText().isEmpty() &&
            !expiryDateField.getText().isEmpty() &&
            !cvvField.getText().isEmpty()) {
            paymentSuccessful = true;
            Stage stage = (Stage) cardNumberField.getScene().getWindow();
            stage.close();
        }
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }
}