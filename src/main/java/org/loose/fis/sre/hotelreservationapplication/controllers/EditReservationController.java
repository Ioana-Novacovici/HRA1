package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class EditReservationController {

    @FXML
    public void goBack(){
        Main.changeToScene("futureReservations.fxml");
    }

    @FXML
    public void updateReservation(){}

}
