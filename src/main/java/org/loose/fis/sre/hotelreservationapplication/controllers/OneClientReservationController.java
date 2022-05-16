package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.loose.fis.sre.hotelreservationapplication.models.Reservation;

public class OneClientReservationController {

    @FXML
    private Label breakfastOption;

    @FXML
    private Label checkIn;

    @FXML
    private Label checkOut;

    @FXML
    private Label extraBedOption;

    @FXML
    private Label parkingOption;

    @FXML
    private Label totalPrice;

    @FXML
    private Label typeOfRoom;

    public void setData(Reservation reservation) {
        if(reservation.isBreakfast()) {
            breakfastOption.setTextFill(Color.valueOf("#14430c"));
        }
        else {
            breakfastOption.setTextFill(Color.valueOf("#c10101"));
        }
        if(reservation.isExtraBed()) {
            extraBedOption.setTextFill(Color.valueOf("#14430c"));
        }
        else {
            extraBedOption.setTextFill(Color.valueOf("#c10101"));
        }
        if(reservation.isParking()) {
            parkingOption.setTextFill(Color.valueOf("#14430c"));
        }
        else {
            parkingOption.setTextFill(Color.valueOf("#c10101"));
        }
        checkIn.setText(String.valueOf(reservation.getStartDate()));
        checkOut.setText(String.valueOf(reservation.getEndDate()));
        totalPrice.setText(String.valueOf(reservation.getPrice()));
        typeOfRoom.setText(reservation.getType());
    }
}
