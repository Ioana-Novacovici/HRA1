package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import org.loose.fis.sre.hotelreservationapplication.Main;

public class ReservationsControler {
    @FXML
    public void goToFutureReservations(){
        Main.changeToScene("futureReservations.fxml");
    }

    @FXML
    public void goToPastReservations(){
        Main.changeToScene("pastReservations.fxml");
    }

    @FXML
    public void goToOngoingReservations(){
        Main.changeToScene("ongoingReservations.fxml");
    }

    @FXML
    public void goToWaitingReservations(){
        Main.changeToScene("waitingReservations.fxml");
    }

    @FXML
    void goBack() { Main.changeToScene("login.fxml");}
}
