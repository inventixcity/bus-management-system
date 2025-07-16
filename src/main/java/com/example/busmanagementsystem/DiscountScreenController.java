package com.example.busmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DiscountScreenController {

    @FXML
    private TextField discountCodeField;

    private double discountPercentage = 0.0;

    @FXML
    private void handleApplyDiscount() {
        String code = discountCodeField.getText().trim();
        if ("DISCOUNT10".equals(code)) {
            discountPercentage = 0.1; // 10% discount
        } else if ("DISCOUNT20".equals(code)) {
            discountPercentage = 0.2; // 20% discount
        }
        closeScreen();
    }

    @FXML
    private void handleSkip() {
        closeScreen();
    }

    private void closeScreen() {
        Stage stage = (Stage) discountCodeField.getScene().getWindow();
        stage.close();
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}