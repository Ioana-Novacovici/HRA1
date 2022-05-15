package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;
import org.loose.fis.sre.hotelreservationapplication.models.Room;

public class OneReservationController {

    @FXML
    private Label endDateLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label userLabel;

    public void setData(Reservation reservation) {
        idLabel.setText(String.valueOf(reservation.getIdReservation()));
        userLabel.setText(String.valueOf(reservation.getUsername()));
        startDateLabel.setText(String.valueOf(reservation.getStartDate()));
        endDateLabel.setText(String.valueOf(reservation.getEndDate()));
        statusLabel.setText(String.valueOf(reservation.getStatus()));
        typeLabel.setText(String.valueOf(reservation.getType()));
    }

}
