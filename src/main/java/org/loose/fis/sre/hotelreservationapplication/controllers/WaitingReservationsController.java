package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaitingReservationsController {
    @FXML
    public void goBack(){
        Main.changeToScene("reservations.fxml");
    }

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {

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
