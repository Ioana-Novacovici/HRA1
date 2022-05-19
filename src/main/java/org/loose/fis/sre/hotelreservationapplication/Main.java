package org.loose.fis.sre.hotelreservationapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;
import org.loose.fis.sre.hotelreservationapplication.services.RoomService;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {

        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Reservation Application");
        stage.setScene(scene);
        stage.show();

    }

    public void switchScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void changeToScene(String fxml){
        try{
            Main m = new Main();
            m.switchScene(fxml);
        }catch (IOException e){}
    }

    public static void main(String[] args) {
        DBConnection.connect("jdbc:mysql://localhost:3306/hoteladministration");
        launch();
        DBConnection.close();
    }
}