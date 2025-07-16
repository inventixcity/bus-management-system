package com.example.busmanagementsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class ViewReservationsController {

    @FXML
    private TableView<Reservation> reservationsTableView;

    @FXML
    private TableColumn<Reservation, Integer> reservationIdColumn;

    @FXML
    private TableColumn<Reservation, String> busNumberColumn;

    @FXML
    private TableColumn<Reservation, String> routeColumn;

    @FXML
    private TableColumn<Reservation, Integer> seatNumberColumn;

    private ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        reservationIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReservationID()).asObject());
        busNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBus().getBusNumber()));
        routeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBus().getRoute()));
        seatNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSeatNumber()).asObject());

        reservationsTableView.setItems(reservationsList);
    }

    public void setReservations(List<Reservation> reservations) {
        reservationsList.clear();
        reservationsList.addAll(reservations);
    }

    @FXML
    private void handleRefresh() {
        // Implement logic to refresh the table data
        List<Reservation> latestReservations = fetchLatestReservations();
        setReservations(latestReservations);
    }

    @FXML
    private void handleBack() {
        // Close the current window
        Stage stage = (Stage) reservationsTableView.getScene().getWindow();
        stage.close();
    }

    private List<Reservation> fetchLatestReservations() {
        // TODO: Implement the logic to fetch the latest reservations from your data source
        // For now, we'll return an empty list. Replace this with actual data retrieval logic.
        return List.of();
    }
}