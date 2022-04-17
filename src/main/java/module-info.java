module org.loose.fis.sre.hotelreservationapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.loose.fis.sre.hotelreservationapplication to javafx.fxml;
    exports org.loose.fis.sre.hotelreservationapplication;
    exports org.loose.fis.sre.hotelreservationapplication.controllers;
    opens org.loose.fis.sre.hotelreservationapplication.controllers to javafx.fxml;
}