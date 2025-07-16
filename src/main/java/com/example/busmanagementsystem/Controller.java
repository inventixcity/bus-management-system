package com.example.busmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private StackPane contentArea;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button mainMenuToAdminLogin;

    @FXML
    private Button backButton;

    private Admin admin;
    private ArrayList<Bus> buses = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Controller() {
        // Initialize the admin with default credentials
        this.admin = new Admin(1, "admin", "password");
    }

    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainMenuToAdminLogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleAdminLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (admin.login(username, password)) {
            showAlert("Login Successful", "Welcome, Admin!");
            initializeAdminData();
            // Switch to admin dashboard
            try {
                root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    @FXML
    public void handleManageBuses() {
        if (admin == null) {
            showAlert("Error", "Admin not logged in.");
            return;
        }

        VBox manageBusesForm = new VBox(10);
        TextField vehicleIdField = new TextField();
        vehicleIdField.setPromptText("Vehicle ID");
        TextField licenseNumberField = new TextField();
        licenseNumberField.setPromptText("License Number");
        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");
        TextField routeField = new TextField();
        routeField.setPromptText("Route");
        TextField busNumberField = new TextField();
        busNumberField.setPromptText("Bus Number");
        Button addBusButton = new Button("Add Bus");
        Button removeBusButton = new Button("Remove Bus");
        Button displayBusesButton = new Button("Display Buses");
        Button backToMainButton = new Button("Back to Main");

        addBusButton.setOnAction(e -> {
            try {
                int vehicleId = Integer.parseInt(vehicleIdField.getText());
                String licenseNumber = licenseNumberField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                String route = routeField.getText();
                String busNumber = busNumberField.getText();
                Bus newBus = new Bus(vehicleId, licenseNumber, capacity, route, busNumber);
                admin.manageBus(buses, "add", newBus);
                showAlert("Bus Added", "Bus has been added successfully.");
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter valid numeric values for Vehicle ID and Capacity.");
            }
        });

        removeBusButton.setOnAction(e -> {
            try {
                int vehicleId = Integer.parseInt(vehicleIdField.getText());
                Bus busToRemove = buses.stream().filter(b -> b.getVehicleID() == vehicleId).findFirst().orElse(null);
                if (busToRemove != null) {
                    admin.manageBus(buses, "remove", busToRemove);
                    showAlert("Bus Removed", "Bus has been removed successfully.");
                } else {
                    showAlert("Error", "Bus not found.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid numeric value for Vehicle ID.");
            }
        });

        displayBusesButton.setOnAction(e -> {
            StringBuilder busInfo = new StringBuilder("Buses:\n");
            for (Bus bus : buses) {
                busInfo.append(bus.getRouteDetails()).append("\n");
            }
            showAlert("Bus Information", busInfo.toString());
        });

        backToMainButton.setOnAction(this::handleBackButton);

        manageBusesForm.getChildren().addAll(vehicleIdField, licenseNumberField, capacityField, routeField, busNumberField, addBusButton, removeBusButton, displayBusesButton, backToMainButton);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(manageBusesForm);
    }

    @FXML
    public void handleViewReservations() {
        if (admin == null) {
            showAlert("Error", "Admin not logged in.");
            return;
        }

        VBox reservationView = new VBox(10);
        ListView<String> reservationListView = new ListView<>();
        Button backToMainButton = new Button("Back to Main");

        admin.viewAllReservations(reservations);
        for (Reservation reservation : reservations) {
            reservationListView.getItems().add(String.format("ID: %d, Bus: %s, Seat: %d, Price: %.2f, Date: %s",
                    reservation.getReservationID(), 
                    reservation.getBus().getBusNumber(),
                    reservation.getSeatNumber(), 
                    reservation.getPrice(),
                    reservation.getReservationDate()));
        }

        backToMainButton.setOnAction(this::handleBackButton);

        reservationView.getChildren().addAll(reservationListView, backToMainButton);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(reservationView);
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        admin = null; // Clear the admin object
        try {
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void initializeAdminData() {
        // Initialize some sample data
        Bus bus1 = new Bus(1, "LIC001", 50, "Route A", "BUS001");
        Bus bus2 = new Bus(2, "LIC002", 40, "Route B", "BUS002");
        buses.add(bus1);
        buses.add(bus2);
        
        Passenger passenger1 = new Passenger(1, "John Doe", "john@example.com");
        Passenger passenger2 = new Passenger(2, "Jane Smith", "jane@example.com");
        
        reservations.add(new Reservation(1, new Date(), 1, "Booked", bus1, passenger1));
        reservations.add(new Reservation(2, new Date(), 2, "Booked", bus2, passenger2));
    }
}