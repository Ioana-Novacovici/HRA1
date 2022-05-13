package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationService {

    public static void addReservation(String username, Date startDate, Date endDate, String type, boolean extraBed, boolean breakfast, boolean parking) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("INSERT INTO reservations (username, startDate, endDate, type, extraBed, breakfast, parking) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, username);
        statement.setDate(2, startDate);
        statement.setDate(3, endDate);
        statement.setString(4, type);
        statement.setString(5, String.valueOf(extraBed));
        statement.setString(6, String.valueOf(breakfast));
        statement.setString(7, String.valueOf(parking));
        statement.executeUpdate();
    }

    public static boolean validateUser(String username) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from users where  username = ?");
        statement.setString(1, username);
        ResultSet user = statement.executeQuery();
        return user.next();
    }

    public static boolean validateDates(LocalDate checkIn, LocalDate checkOut) throws SQLException {

        LocalDate today = LocalDate.now();
        if (!checkIn.isBefore(checkOut) || !checkIn.isAfter(today) || !checkOut.isAfter(today))
            return false;
        return true;
    }

}
