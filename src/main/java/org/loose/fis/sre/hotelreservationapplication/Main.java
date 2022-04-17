package org.loose.fis.sre.hotelreservationapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Reservation");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        DBConnection.connect();
        launch();
        DBConnection.close();
    }
}