package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class FutureReservationsController {

    @FXML
    private GridPane gridPane;

    @FXML
    public void goAddReservation(){
        Main.changeToScene("addReservation.fxml");
    }

    @FXML
    public void goBack(){
        Main.changeToScene("reservations.fxml");
    }


}
