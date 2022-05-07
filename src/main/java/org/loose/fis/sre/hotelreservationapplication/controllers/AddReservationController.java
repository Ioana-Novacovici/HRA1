package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;

import java.sql.SQLException;

public class AddReservationController {

    @FXML
    private Label errorMessage;

    @FXML
    private Label successMessage;

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
    public void saveReservation() {
        try {
            if (usernameField.getText().isEmpty() || checkInDate.getValue() == null || checkOutDate.getValue() == null || typeOfRoom.getValue() == null){
                errorMessage.setText("Please fill in all the fields!");
                successMessage.setText("");
            }else if(ReservationService.validateUser(usernameField.getText()) == false){
                errorMessage.setText("Username does not exist!");
                successMessage.setText("");
            }else {
                java.sql.Date date1 = java.sql.Date.valueOf(checkInDate.getValue());
                java.sql.Date date2 = java.sql.Date.valueOf(checkOutDate.getValue());
                ReservationService.addReservation(usernameField.getText(), date1, date2, typeOfRoom.getValue().toString(), extraBedCheck.isSelected(), breakfastCheck.isSelected(), parkingCheck.isSelected());
                errorMessage.setText("");
                successMessage.setText("Reservation created successfully!");

            }
        } catch (SQLException e) {
            errorMessage.setText("Something went wrong! Please try again!");
        }
    }

}

