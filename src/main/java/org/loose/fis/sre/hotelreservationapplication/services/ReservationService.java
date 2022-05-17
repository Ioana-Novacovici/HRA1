package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
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

    public static void addReservation(String username, Date startDate, Date endDate, String status, String type, boolean extraBed, boolean breakfast, boolean parking, int totPrice) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("INSERT INTO reservations (username, startDate, endDate, status, type, extraBed, breakfast, parking, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, username);
        statement.setDate(2, startDate);
        statement.setDate(3, endDate);
        statement.setString(4, status);
        statement.setString(5, type);
        statement.setString(6, String.valueOf(extraBed));
        statement.setString(7, String.valueOf(breakfast));
        statement.setString(8, String.valueOf(parking));
        statement.setInt(9, totPrice);
        statement.executeUpdate();
    }

    public static int getPrice(String roomType, int days, boolean extraBed, boolean breakfast, boolean parking) throws SQLException{
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from rooms where  type = ?");
        statement.setString(1, roomType);
        ResultSet room = statement.executeQuery();
        room.next();
        int totalPrice = room.getInt(3) * days;
        if(extraBed)
            totalPrice += 50 * days;
        if(breakfast)
            totalPrice += 20 * days * room.getInt(2);
        if(parking)
            totalPrice += 5 * days;
        return totalPrice;
    }

    public static int daysBetween(LocalDate date1, LocalDate date2) {
        return Period.between(date1, date2).getDays();
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

    public static ResultSet getClientReservations(String username) throws SQLException{
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE username = ?");
        statement.setString(1, username);
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

    public static ArrayList<Integer> getFutureReservationsID() throws SQLException{
        ArrayList<Integer> futureIDs = new ArrayList<>();
        ResultSet futureRes = ReservationService.getFutureReservations();
        while(futureRes.next()) {
            futureIDs.add(futureRes.getInt(1));
        }
        return futureIDs;
    }

    public static void updateReservation(Integer ReservationID, Date startDate, Date endDate, String type, boolean extraBed, boolean breakfast, boolean parking, int totPrice) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("UPDATE reservations SET startDate=?, endDate=?, type=?, extraBed=?, breakfast=?, parking=?, price=? WHERE idReservation=? ");
        statement.setDate(1, startDate);
        statement.setDate(2, endDate);
        statement.setString(3, type);
        statement.setString(4, String.valueOf(extraBed));
        statement.setString(5, String.valueOf(breakfast));
        statement.setString(6, String.valueOf(parking));
        statement.setInt(7, totPrice);
        statement.setString(8, String.valueOf(ReservationID));
        statement.executeUpdate();
    }

    public static ArrayList<Integer> getWaitingReservationsID() throws SQLException{
        ArrayList<Integer> waitingIDs = new ArrayList<>();
        ResultSet waitingRes = ReservationService.getWaitingReservations();
        while(waitingRes.next()) {
            waitingIDs.add(waitingRes.getInt(1));
        }
        return waitingIDs;
    }

    public static void acceptReservation(Integer ReservationID) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("UPDATE reservations SET status = 'accepted' WHERE idReservation=? ");
        statement.setInt(1, ReservationID);
        statement.executeUpdate();
    }

    public static void rejectReservation(Integer ReservationID) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("UPDATE reservations SET status = 'rejected' WHERE idReservation=? ");
        statement.setInt(1, ReservationID);
        statement.executeUpdate();
    }

    public static ArrayList<Integer> getPastReservationsID() throws SQLException{
        ArrayList<Integer> pastIDs = new ArrayList<>();
        ResultSet pastRes = ReservationService.getPastReservations();
        while(pastRes.next()) {
            pastIDs.add(pastRes.getInt(1));
        }
        return pastIDs;
    }

    public static void deleteReservation(Integer ReservationID) throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE FROM reservations WHERE idReservation=? ");
        statement.setInt(1, ReservationID);
        statement.executeUpdate();
    }

}
