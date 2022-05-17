package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;
import org.loose.fis.sre.hotelreservationapplication.services.UserService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientReservationsController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void goBack(){
        Main.changeToScene("rooms.fxml");
    }

    @FXML
    public void initialize() {

        try{
            ResultSet reservations = ReservationService.getClientReservations(UserService.getMyUser());
            int row = 1;
            while(reservations.next()) {
                //trebuie incarcat fxml cu rezervare vazuta de client
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneClientReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Reservation reservation = new Reservation();
                reservation.setStartDate(reservations.getDate(3));
                reservation.setEndDate(reservations.getDate(4));
                reservation.setStatus(reservations.getString(5));
                reservation.setType(reservations.getString(6));
                reservation.setExtraBed(reservations.getBoolean(7));
                reservation.setBreakfast(reservations.getBoolean(8));
                reservation.setParking(reservations.getBoolean(9));
                reservation.setPrice(reservations.getInt(10));
                OneClientReservationController oneClientReservationController = fxmlLoader.getController();
                oneClientReservationController.setData(reservation);
                grid.add(anchorPane, 0, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }


}
