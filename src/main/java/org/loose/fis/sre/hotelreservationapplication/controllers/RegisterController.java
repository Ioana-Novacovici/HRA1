package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.services.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Label registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || (String) role.getValue() == null || fullNameField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
                registrationMessage.setText("Please fill in all the fields!");
            } else if (UserService.validatePhoneNumber(phoneNumberField.getText()) == false) {
                registrationMessage.setText("Invalid phone number!");
            } else if (UserService.validateFullName(fullNameField.getText()) == false) {
                registrationMessage.setText("Enter a valid name!");
            }else {
                UserService.addUser(usernameField.getText(), UserService.encodePassword(passwordField.getText()), (String) role.getValue(), fullNameField.getText(), phoneNumberField.getText());
                registrationMessage.setText("Account created successfully!");
                if(((String) role.getValue()).equals("Client")) {
                    try{
                        Main m = new Main();
                        m.switchScene("rooms.fxml");
                    }catch (IOException e){
                        System.out.println(e);
                    }
                }
            }
        } catch (SQLException e) {
            registrationMessage.setText("Username already exists!");
        }
    }
}