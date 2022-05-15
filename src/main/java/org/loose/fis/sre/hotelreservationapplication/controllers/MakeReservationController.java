package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Room;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;
import org.loose.fis.sre.hotelreservationapplication.services.RoomService;
import org.loose.fis.sre.hotelreservationapplication.services.UserService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

public class MakeReservationController {

    @FXML
    private CheckBox breakfastCheck;

    @FXML
    private Label dates;

    @FXML
    private CheckBox extraBedCheck;

    @FXML
    private GridPane grid;

    @FXML
    private CheckBox parkingCheck;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label errorMessage;

    @FXML
    private ComboBox typeOfRoom;

    @FXML
    public void initialize() {
        dates.setText(ReservationService.getDate1() + " - " + ReservationService.getDate2());
        try{
            ResultSet rooms = RoomService.getAvailableRooms();
            HashMap<String, Integer> roomsMap =  ReservationService.getNumberOfReservations();
            ArrayList<String> availableRoomsList = new ArrayList<>();
            availableRoomsList.add("Single Room");
            availableRoomsList.add("Double Room");
            availableRoomsList.add("Triple Room");
            availableRoomsList.add("Family Room");
            availableRoomsList.add("Apartment");

            for(String s: roomsMap.keySet()) {
                if(roomsMap.get(s) >= 10) {
                    availableRoomsList.remove(s);
                }
            }

            typeOfRoom.getItems().addAll(availableRoomsList);

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
                if(availableRoomsList.contains(room.getType())) {
                    anchorPane.setStyle("-fx-background-color:  #92e892");
                } else {
                    anchorPane.setStyle("-fx-background-color: #e08686");
                }
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void goBack() { Main.changeToScene("rooms.fxml");}

    @FXML
    void bookNow() {
        try {
            if (typeOfRoom.getValue() == null){
                errorMessage.setText("Please select the room you want!");
            }else {
                java.sql.Date date1 = java.sql.Date.valueOf(ReservationService.getDate1());
                java.sql.Date date2 = java.sql.Date.valueOf(ReservationService.getDate2());
                ReservationService.addReservation(UserService.getMyUser(), date1, date2, "waiting", typeOfRoom.getValue().toString(), extraBedCheck.isSelected(), breakfastCheck.isSelected(), parkingCheck.isSelected());
                Main.changeToScene("rooms.fxml");
            }
        } catch (SQLException e) {
            errorMessage.setText("Something went wrong! Please try again!");
        }
    }
}
