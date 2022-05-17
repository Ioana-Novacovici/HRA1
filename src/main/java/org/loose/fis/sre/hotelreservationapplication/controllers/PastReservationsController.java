package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PastReservationsController {
    @FXML
    private Label Message;

    @FXML
    private Button deleteButton;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox reservationID;

    @FXML
    void handleDelete() {
        try{
            if(reservationID.getValue() == null){
                Message.setTextFill(Color.RED);
                Message.setText("Select an ID!");
            }else{
                ReservationService.deleteReservation((Integer) reservationID.getValue());
                Message.setTextFill(Color.GREEN);
                Message.setText("Reservation deleted!");
            }
        }catch(SQLException e){
            Message.setTextFill(Color.RED);
            Message.setText("Something went wrong!");
        }
    }

    @FXML
    public void goBack(){
        Main.changeToScene("reservations.fxml");
    }


    @FXML
    public void initialize() {

        try{
            ArrayList<Integer> pastIDS = ReservationService.getPastReservationsID();
            if(pastIDS.isEmpty()) {
                reservationID.getItems().clear();
                reservationID.setPromptText("No past reservations");
                deleteButton.setVisible(false);
            } else {
                reservationID.getItems().addAll(pastIDS);
            }
        }catch(SQLException e){
            System.out.println(e);
        }

        try{
            ResultSet reservations = ReservationService.getPastReservations();
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
