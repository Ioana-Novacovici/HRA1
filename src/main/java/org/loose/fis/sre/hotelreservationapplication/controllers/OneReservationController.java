package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;

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
        userLabel.setText(reservation.getUsername());
        startDateLabel.setText(String.valueOf(reservation.getStartDate()));
        endDateLabel.setText(String.valueOf(reservation.getEndDate()));
        statusLabel.setText(reservation.getStatus());
        typeLabel.setText(reservation.getType());
    }

}
