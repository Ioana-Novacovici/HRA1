package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Room;
import org.loose.fis.sre.hotelreservationapplication.services.ReservationService;
import org.loose.fis.sre.hotelreservationapplication.services.RoomService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MakeReservationController {

    @FXML
    private Label dates;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void initialize() {
        dates.setText(ReservationService.getDate1() + " - " + ReservationService.getDate2());
        try{
            ResultSet rooms = RoomService.getAvailableRooms();
            ReservationService.getNumberOfReservations();
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
    public void goBack() { Main.changeToScene("rooms.fxml");}
}