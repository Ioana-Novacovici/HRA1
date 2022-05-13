package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Room;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;
import org.loose.fis.sre.hotelreservationapplication.services.RoomService;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RoomsController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize() {

        try{
            ResultSet rooms = RoomService.getRooms();
            int row = 1;
            while(rooms.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneRoom.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Room room = new Room();
                room.setType(rooms.getString(1));
                room.setNrPers(rooms.getInt(2));
                room.setPrice(rooms.getInt(3));
                room.setImg(rooms.getString(5));
                OneRoomController oneRoomController = fxmlLoader.getController();
                oneRoomController.setData(room);
                grid.add(anchorPane, 0, row++); //(child,column,row)

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }

    @FXML
    public void checkingAvailability() {

        try{
            //verifica availability ...
            //validare date
            LocalDate date1 = checkInDate.getValue();
            LocalDate date2 = checkOutDate.getValue();

            if(checkOutDate.getValue() == null || checkInDate.getValue() == null) {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Please fill in all the fields!");
            } else if(ReservationService.validateDates(date1, date2) == true) {
                errorMessage.setTextFill(Color.GREEN);
                errorMessage.setText("Valid dates");
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Invalid dates");
            }
            //switch scene
        } catch (SQLException e) {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Something went wrong! Please try again!");
        }

    }

    @FXML
    public void logOut() { Main.changeToScene("login.fxml");}

}
