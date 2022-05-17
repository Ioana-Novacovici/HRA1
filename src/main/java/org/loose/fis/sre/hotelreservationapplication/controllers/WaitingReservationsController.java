package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitingReservationsController {

    @FXML
    private Button acceptButton;

    @FXML
    private Button rejectButton;

    @FXML
    private ComboBox reservationID;

    @FXML
    private GridPane grid;

    @FXML
    void handleAccept() {

    }

    @FXML
    void handleReject() {

    }

    @FXML
    public void goBack(){
        Main.changeToScene("reservations.fxml");
    }

    @FXML
    public void initialize() {

        try{
            ArrayList<Integer> waitingIDS = ReservationService.getWaitingReservationsID();
            if(waitingIDS.isEmpty()) {
                reservationID.getItems().clear();
                reservationID.setPromptText("No waiting reservations");
                acceptButton.setVisible(false);
                rejectButton.setVisible(false);

            } else {
                reservationID.getItems().addAll(waitingIDS);
            }
        }catch(SQLException e){
            System.out.println(e);
        }

        try{
            ResultSet reservations = ReservationService.getWaitingReservations();
            int row = 1;
            while(reservations.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Reservation reservation = new Reservation();
                reservation.setIdReservation(reservations.getInt(1));
                reservation.setUsername(reservations.getString(2));
                reservation.setStartDate(reservations.getDate(3));
                reservation.setEndDate(reservations.getDate(4));
                reservation.setStatus(reservations.getString(5));
                reservation.setType(reservations.getString(6));
                OneReservationController oneReservationController = fxmlLoader.getController();
                oneReservationController.setData(reservation);
                grid.add(anchorPane, 0, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }

}
