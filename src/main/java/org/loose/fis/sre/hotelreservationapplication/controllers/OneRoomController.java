package org.loose.fis.sre.hotelreservationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.sre.hotelreservationapplication.Main;
import org.loose.fis.sre.hotelreservationapplication.models.Room;

import java.io.File;

public class OneRoomController {

    @FXML
    private Label nrPers;

    @FXML
    private Label price;

    @FXML
    private ImageView roomImg;

    @FXML
    private Label type;

    public void setData(Room room) {
        nrPers.setText(String.valueOf(room.getNrPers()));
        price.setText(String.valueOf(room.getPrice()));
        type.setText(room.getType());
        String imgAddress = "C:\\Users\\Sonia\\OneDrive - Universitatea Politehnica Timisoara\\AN II\\Sem II\\FIS\\HotelReservationApplication\\src\\main\\resources\\org\\loose\\fis\\sre\\hotelreservationapplication\\images\\apartament.jpg";
        Image image = new Image(imgAddress);
        roomImg.setImage(image);
    }
}
