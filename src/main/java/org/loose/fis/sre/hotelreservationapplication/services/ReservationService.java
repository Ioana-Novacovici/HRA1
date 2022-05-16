package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ReservationService {

    private static LocalDate date1, date2;

    public static LocalDate getDate1() {
        return date1;
    }

    public static LocalDate getDate2() {
        return date2;
    }

    public static void addReservation(String username, Date startDate, Date endDate, String status, String type, boolean extraBed, boolean breakfast, boolean parking) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("INSERT INTO reservations (username, startDate, endDate, status, type, extraBed, breakfast, parking) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, username);
        statement.setDate(2, startDate);
        statement.setDate(3, endDate);
        statement.setString(4, status);
        statement.setString(5, type);
        statement.setString(6, String.valueOf(extraBed));
        statement.setString(7, String.valueOf(breakfast));
        statement.setString(8, String.valueOf(parking));
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

    public static HashMap<String, Integer> getNumberOfReservations() throws SQLException{
        HashMap<String, Integer> roomsMap = new HashMap<>();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT TYPE, COUNT(idReservation) AS 'rezervari' FROM reservations WHERE not (startDate >= ? or endDate <= ?) and (STATUS = 'accepted' or STATUS = 'waiting') GROUP BY TYPE");
        statement.setString(1, ReservationService.getDate2().toString());
        statement.setString(2, ReservationService.getDate1().toString());
        ResultSet rooms = statement.executeQuery();
        while(rooms.next()) {
            roomsMap.put(rooms.getString(1), rooms.getInt(2));
        }
        return roomsMap;

    }

    public static ResultSet getFutureReservations () throws SQLException {

        LocalDate today = LocalDate.now();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE status = 'accepted' AND  startDate > ?");
        statement.setString(1, String.valueOf(today));
        ResultSet reservations = statement.executeQuery();
        return reservations;
    }

    public static ResultSet getPastReservations () throws SQLException {

        LocalDate today = LocalDate.now();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE status = 'rejected' OR  endDate < ?");
        statement.setString(1, String.valueOf(today));
        ResultSet reservations = statement.executeQuery();
        return reservations;
    }

    public static ResultSet getWaitingReservations () throws SQLException {

        LocalDate today = LocalDate.now();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE status = 'waiting' AND startDate > ?");
        statement.setString(1, String.valueOf(today));
        ResultSet reservations = statement.executeQuery();
        return reservations;
    }

    public static ResultSet getOngoingReservations () throws SQLException {

        LocalDate today = LocalDate.now();
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE startDate <= ? AND endDate >= ? AND status = 'accepted'");
        statement.setString(1, String.valueOf(today));
        statement.setString(2, String.valueOf(today));
        ResultSet reservations = statement.executeQuery();
        return reservations;
    }

    public static boolean checkAvailability(String typeOfRoom) throws SQLException{
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
        return availableRoomsList.contains(typeOfRoom);
    }

}
