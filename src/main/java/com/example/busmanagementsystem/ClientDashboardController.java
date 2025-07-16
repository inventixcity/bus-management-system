package com.example.busmanagementsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDashboardController {

    @FXML
    private TableView<Bus> busTableView;
    @FXML
    private TableColumn<Bus, Integer> busIdColumn;
    @FXML
    private TableColumn<Bus, String> routeColumn;
    @FXML
    private TableColumn<Bus, Integer> capacityColumn;
    @FXML
    private TableColumn<Bus, Integer> availableSeatsColumn;

    private ObservableList<Bus> busList = FXCollections.observableArrayList();
    private List<Reservation> reservations = new ArrayList<>();

    @FXML
    private void initialize() {
        busIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVehicleID()).asObject());
        routeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoute()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());
        availableSeatsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAvailableSeats().size()).asObject());

        busTableView.setItems(busList);
    }

    @FXML
    private void handleViewBuses() {
        // For demonstration, we'll add some dummy data
        busList.clear();
        busList.addAll(
            new Bus(1, "LHR-123", 50, "Lahore to Islamabad", "B001"),
            new Bus(2, "KHI-456", 40, "Karachi to Lahore", "B002"),
            new Bus(3, "ISB-789", 45, "Islamabad to Peshawar", "B003")
        );
    }

    @FXML
    private void handleMakeReservation() {
        Bus selectedBus = busTableView.getSelectionModel().getSelectedItem();
        if (selectedBus != null) {
            makeReservation(selectedBus);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a bus to make a reservation.");
            alert.showAndWait();
        }
    }

    private void makeReservation(Bus bus) {
    List<Seat> availableSeats = bus.getAvailableSeats();
    if (availableSeats.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Available Seats");
        alert.setHeaderText(null);
        alert.setContentText("Sorry, there are no available seats on this bus.");
        alert.showAndWait();
        return;
    }

    ChoiceDialog<Integer> dialog = new ChoiceDialog<>(availableSeats.get(0).getSeatNumber(), 
        availableSeats.stream().map(Seat::getSeatNumber).toList());
    dialog.setTitle("Select Seat");
    dialog.setHeaderText("Choose a seat number for your reservation");
    dialog.setContentText("Available seats:");

    Optional<Integer> result = dialog.showAndWait();
    if (result.isPresent()) {
        int seatNumber = result.get();
        try {
            // Show discount screen
            FXMLLoader discountLoader = new FXMLLoader(getClass().getResource("DiscountScreen.fxml"));
            Parent discountRoot = discountLoader.load();
            Stage discountStage = new Stage();
            discountStage.setScene(new Scene(discountRoot));
            discountStage.showAndWait();

            DiscountScreenController discountController = discountLoader.getController();
            double discountPercentage = discountController.getDiscountPercentage();

            // Calculate total amount (you may want to set a base price for the bus ticket)
            double basePrice = 50.0; // Example base price
            double totalAmount = basePrice * (1 - discountPercentage);

            // Show payment screen
            FXMLLoader paymentLoader = new FXMLLoader(getClass().getResource("PaymentScreen.fxml"));
            Parent paymentRoot = paymentLoader.load();
            PaymentScreenController paymentController = paymentLoader.getController();
            paymentController.setTotalAmount(totalAmount);

            Stage paymentStage = new Stage();
            paymentStage.setScene(new Scene(paymentRoot));
            paymentStage.showAndWait();

            if (paymentController.isPaymentSuccessful()) {
                Passenger passenger = new Passenger(1, "John Doe", "1234567890"); // Create a passenger object
                Reservation reservation = Reservation.makeReservation(reservations.size() + 1, passenger, bus, seatNumber);
                
                if (reservation != null) {
                    reservations.add(reservation);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reservation Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Your reservation has been made successfully!\n" +
                                         "Reservation ID: " + reservation.getReservationID() + "\n" +
                                         "Bus: " + bus.getBusNumber() + "\n" +
                                         "Seat: " + seatNumber + "\n" +
                                         "Total Amount Paid: $" + String.format("%.2f", totalAmount));
                    alert.showAndWait();
                    
                    // Refresh the bus list to show updated available seats
                    busTableView.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Booking Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to make the reservation. The seat might be already booked.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reservation Cancelled");
                alert.setHeaderText(null);
                alert.setContentText("The reservation was cancelled due to unsuccessful payment.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while processing your reservation.");
            alert.showAndWait();
        } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Booking Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to book the seat: " + e.getMessage());
            alert.showAndWait();
        }
    }
}

    @FXML
    private void handleViewReservations() {
        if (reservations.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Reservations");
            alert.setHeaderText(null);
            alert.setContentText("You have no current reservations.");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewReservations.fxml"));
                Parent root = loader.load();
                
                ViewReservationsController viewReservationsController = loader.getController();
                viewReservationsController.setReservations(reservations);
                
                Stage stage = new Stage();
                stage.setTitle("View Reservations");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to load view reservations page.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) busTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load login page.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) busTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to return to the login page.");
            alert.showAndWait();
        }
    }
}