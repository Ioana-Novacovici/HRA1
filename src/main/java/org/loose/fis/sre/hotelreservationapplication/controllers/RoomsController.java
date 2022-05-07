package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Room;
import org.loose.fis.sre.hotelreservationapplication.services.RoomService;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomsController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

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
                OneRoomController oneRoomController = fxmlLoader.getController();
                oneRoomController.setData(room);
                grid.add(anchorPane, 0, row++); //(child,column,row)

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }

}
