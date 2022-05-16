package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class OngoingReservationsController {
    @FXML
    public void goBack(){
        Main.changeToScene("reservations.fxml");
    }

}
