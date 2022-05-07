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
        String imgAddress = room.getImg();
        Image image = new Image(imgAddress);
        roomImg.setImage(image);
    }
}
