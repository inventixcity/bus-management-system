package com.example.busmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private Button clientLoginButton;

    @FXML
    private Button adminLoginButton;

    @FXML
    private VBox loginForm;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    private boolean isAdminLogin = false;
    private Map<String, UserCredential> userCredentials;

    @FXML
    private void initialize() {
        if (messageLabel == null) {
            messageLabel = new Label();
            messageLabel.setTextFill(Color.RED);
            if (loginForm != null) {
                loginForm.getChildren().add(messageLabel);
            }
        }
        loadUserCredentials();
    }

    private void loadUserCredentials() {
        userCredentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/users.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    userCredentials.put(parts[0], new UserCredential(parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Error loading user credentials", true);
        }
    }

    @FXML
    private void handleClientLogin() {
        isAdminLogin = false;
        showLoginForm("Client Login");
    }

    @FXML
    private void handleAdminLogin() {
        isAdminLogin = true;
        showLoginForm("Admin Login");
    }

    @FXML
    private void handleLogin() {
        if (usernameField == null || passwordField == null) {
            showMessage("Error: Login fields not initialized", true);
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Please enter both username and password.", true);
            return;
        }

        UserCredential userCredential = userCredentials.get(username);
        if (userCredential != null && userCredential.password.equals(password)) {
            if ((isAdminLogin && userCredential.role.equals("ADMIN")) || (!isAdminLogin && userCredential.role.equals("CLIENT"))) {
                showMessage(userCredential.role + " login successful", false);
                navigateToDashboard(userCredential.role + "Dashboard.fxml");
            } else {
                showMessage("Invalid credentials for " + (isAdminLogin ? "admin" : "client") + " login", true);
            }
        } else {
            showMessage("Invalid username or password", true);
        }
    }

    private void showLoginForm(String title) {
        if (loginForm != null) loginForm.setVisible(true);
        if (messageLabel != null) messageLabel.setText("");
        if (usernameField != null) usernameField.clear();
        if (passwordField != null) passwordField.clear();
        if (loginButton != null) loginButton.setText(title);
    }

    private void showMessage(String message, boolean isError) {
        if (messageLabel != null) {
            messageLabel.setText(message);
            messageLabel.setTextFill(isError ? Color.RED : Color.GREEN);
        }
    }

    private void navigateToDashboard(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Error loading dashboard: " + e.getMessage(), true);
        }
    }

    private static class UserCredential {
        String password;
        String role;

        UserCredential(String password, String role) {
            this.password = password;
            this.role = role;
        }
    }
}