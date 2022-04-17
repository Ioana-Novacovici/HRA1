package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.hotelreservationapplication.services.UserService;

import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Text registrationMessage;
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
            if (usernameField.getText() == "" || passwordField.getText() == "" || (String) role.getValue() == "" || fullNameField.getText() == "" || phoneNumberField.getText() == "") {
                registrationMessage.setText("Please fill in all the fields!");
            } else {
                UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(), fullNameField.getText(), phoneNumberField.getText());
                registrationMessage.setText("Account created successfully!");
            }
        } catch (SQLException e) {
            registrationMessage.setText("Username already exists!");
            System.out.println(e);
        }
    }
}