package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditReservationController {

    @FXML
    private Label Message;

    @FXML
    private CheckBox breakfastCheck;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private CheckBox extraBedCheck;

    @FXML
    private CheckBox parkingCheck;

    @FXML
    private ComboBox reservationID;

    @FXML
    private ChoiceBox typeOfRoom;

    @FXML
    public void goBack(){
        Main.changeToScene("futureReservations.fxml");
    }

    @FXML
    public void initialize() {
        typeOfRoom.getItems().addAll("Single Room", "Double Room", "Triple Room", "Family Room", "Apartment");
        try{
            ArrayList<Integer> futureIDS = ReservationService.getFutureReservationsID();
            if(futureIDS.isEmpty()) {
                reservationID.getItems().clear();
                reservationID.setPromptText("No available rooms");
            } else {
                reservationID.getItems().addAll(futureIDS);
            }
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    @FXML
    public void updateReservation(){
        try {
            LocalDate dateStart = checkInDate.getValue();
            LocalDate dateEnd = checkOutDate.getValue();
            if (reservationID.getValue() == null || checkInDate.getValue() == null || checkOutDate.getValue() == null || typeOfRoom.getValue() == null){
                Message.setTextFill(Color.RED);
                Message.setText("Please fill in all the fields!");
            }else if(ReservationService.validateDates(dateStart, dateEnd) == false){
                Message.setTextFill(Color.RED);
                Message.setText("Invalid dates!");
            }else if(ReservationService.checkAvailability((String) typeOfRoom.getValue()) == false){
                Message.setTextFill(Color.RED);
                Message.setText("Room not available now!");

            }else {
                java.sql.Date date1 = java.sql.Date.valueOf(checkInDate.getValue());
                java.sql.Date date2 = java.sql.Date.valueOf(checkOutDate.getValue());
                ReservationService.updateReservation((Integer) reservationID.getValue(), date1, date2, typeOfRoom.getValue().toString(), extraBedCheck.isSelected(), breakfastCheck.isSelected(), parkingCheck.isSelected());
                Message.setTextFill(Color.GREEN);
                Message.setText("Reservation updated successfully!");

            }
        } catch (SQLException e) {
            Message.setTextFill(Color.RED);
            Message.setText("Something went wrong! Please try again!");
        }
    }

}
