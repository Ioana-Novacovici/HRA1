package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class ReservationsControler {
    @FXML
    public void goToFutureReservations(){
        Main.changeToScene("futureReservations.fxml");
    }


}
