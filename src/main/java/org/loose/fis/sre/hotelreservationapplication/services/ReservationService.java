package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class ReservationService {

    private static LocalDate date1, date2;

    public static LocalDate getDate1() {
        return date1;
    }

    public static LocalDate getDate2() {
        return date2;
    }

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
        date1 = checkIn;
        date2 = checkOut;
        return true;
    }

    public static void getNumberOfReservations() throws SQLException{
        HashMap<String, Integer> roomsMap = new HashMap<>();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT TYPE, COUNT(idReservation) AS 'rezervari' FROM reservations WHERE not (startDate >= ? or endDate <= ?) and (STATUS = 'accepted' or STATUS = 'waiting') GROUP BY TYPE");
        statement.setString(1, ReservationService.getDate2().toString());
        statement.setString(2, ReservationService.getDate1().toString());
        ResultSet rooms = statement.executeQuery();
        while(rooms.next()) {
            roomsMap.put(rooms.getString(1), rooms.getInt(2));
        }
        for(String s: roomsMap.keySet()) {
            String key = s.toString();
            int value = roomsMap.get(s);
            System.out.println(key + " " + value);
        }

    }

}