package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddReservationController {

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
    private ChoiceBox typeOfRoom;


    @FXML
    private TextField usernameField;

    @FXML
    public void initialize() {
        typeOfRoom.getItems().addAll("Single Room", "Double Room", "Triple Room", "Family Room", "Apartament");
    }

    @FXML
    public void goBack(){
        Main.changeToScene("futureReservations.fxml");
    }

    @FXML
    public void saveReservation() {
        try {
            LocalDate dateStart = checkInDate.getValue();
            LocalDate dateEnd = checkOutDate.getValue();
            if (usernameField.getText().isEmpty() || checkInDate.getValue() == null || checkOutDate.getValue() == null || typeOfRoom.getValue() == null){
                Message.setTextFill(Color.RED);
                Message.setText("Please fill in all the fields!");
            }else if(ReservationService.validateUser(usernameField.getText()) == false){
                Message.setTextFill(Color.RED);
                Message.setText("Username does not exist!");
            }else if(ReservationService.validateDates(dateStart, dateEnd) == false){
                Message.setTextFill(Color.RED);
                Message.setText("Invalid dates!");
            }else if(ReservationService.checkAvailability((String) typeOfRoom.getValue()) == false){
                Message.setTextFill(Color.RED);
                Message.setText("Room not available now!");

            }else {
                java.sql.Date date1 = java.sql.Date.valueOf(checkInDate.getValue());
                java.sql.Date date2 = java.sql.Date.valueOf(checkOutDate.getValue());
                ReservationService.addReservation(usernameField.getText(), date1, date2, "accepted", typeOfRoom.getValue().toString(), extraBedCheck.isSelected(), breakfastCheck.isSelected(), parkingCheck.isSelected());
                Message.setTextFill(Color.GREEN);
                Message.setText("Reservation created successfully!");

            }
        } catch (SQLException e) {
            Message.setTextFill(Color.RED);
            Message.setText("Something went wrong! Please try again!");
        }
    }

}

