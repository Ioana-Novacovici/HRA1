package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.services.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Label loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    @FXML
    public void handleLoginAction() {
        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || (String) role.getValue() == null) {
                loginMessage.setText("Please fill in all the fields!");
            } else if (UserService.searchUser(usernameField.getText(), UserService.encodePassword(passwordField.getText()), (String) role.getValue()) == true){
                loginMessage.setText("Logged in as: " + (String)role.getValue()); //switch scene-later...
            } else {
                loginMessage.setText("Invalid username or password!");
            }
        } catch (SQLException e) {
            loginMessage.setText("Something went wrong! Please try again.");
        }
    }

    @FXML
    public void goRegisterAction(){
        try{
            Main m = new Main();
            m.switchScene("register.fxml");
        }catch (IOException e){}
    }

}
