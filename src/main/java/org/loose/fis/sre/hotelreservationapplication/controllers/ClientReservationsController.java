package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class ClientReservationsController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void goBack(){
        Main.changeToScene("rooms.fxml");
    }

}
