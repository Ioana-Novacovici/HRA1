package org.loose.fis.sre.hotelreservationapplication.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class ReservationServiceTest {

    @BeforeAll
    static void beforeAll(){
        DBConnection.connect("jdbc:mysql://localhost:3306/hoteladministrationtest");
    }

    @AfterAll
    static void afterAll(){
        DBConnection.close();
    }

    @Test
    void addReservationTest() throws SQLException {
        java.sql.Date d1 = new java.sql.Date(2022, 1, 12);
        java.sql.Date d2 = new java.sql.Date(2022, 2, 12);
        ReservationService.addReservation("ioana", d1, d2, "rejected", "Single Room", true, true, true, 500);
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from reservations WHERE username = 'ioana' ");
        ResultSet reservation = statement.executeQuery();
        Assertions.assertNotNull(reservation.next());

        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }

    @Test
    void getPriceTest() throws SQLException{
        int price = ReservationService.getPrice("Apartment", 1, true, true, true);
        Assertions.assertEquals(price, 775);
    }

    @Test
    void daysBetweenTest(){
        LocalDate d1 = LocalDate.now();
        int days = ReservationService.daysBetween(d1, d1);
        Assertions.assertEquals(days, 0);
    }

    @Test
    void validateUserTest() throws SQLException{
        UserService.addUser("ioana", "password", "role", "name", "1234567897");
        Assertions.assertTrue(ReservationService.validateUser("ioana"));

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from users");
        statement.executeUpdate();
    }

    @Test
    void validateDatesTest() throws SQLException{
        LocalDate date= LocalDate.now();
        Assertions.assertFalse(ReservationService.validateDates(date, date));
    }

    @Test
    void getFutureReservationsTest() throws SQLException{
        Date date1 = new Date(2035, 10, 12);
        Date date2 = new Date(2035, 12, 12);
        ReservationService.addReservation("ioana", date1, date2, "accepted", "Single Room", true, true, true, 500);
        ResultSet reservation = ReservationService.getFutureReservations();
        Assertions.assertTrue(reservation.next());

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }

    @Test
    void getPastReservationsTest() throws SQLException{
        LocalDate date= LocalDate.now();
        ReservationService.addReservation("ioana", Date.valueOf(date), Date.valueOf(date), "accepted", "Single Room", true, true, true, 500);
        ResultSet reservation = ReservationService.getPastReservations();
        Assertions.assertFalse(reservation.next());

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }

    @Test
    void getOngoingReservationsTest() throws SQLException{
        LocalDate date= LocalDate.now();
        Date date2 = new Date(2035, 12, 12);
        ReservationService.addReservation("ioana", Date.valueOf(date), date2, "accepted", "Single Room", true, true, true, 500);
        ResultSet reservation = ReservationService.getOngoingReservations();
        Assertions.assertTrue(reservation.next());

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }

    @Test
    void getWaitingReservationsTest() throws SQLException{
        LocalDate date= LocalDate.now();
        ReservationService.addReservation("ioana", Date.valueOf(date), Date.valueOf(date), "waiting", "Single Room", true, true, true, 500);
        ResultSet reservation = ReservationService.getWaitingReservations();
        Assertions.assertTrue(reservation.next());

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }

    @Test
    void getClientReservationsTest() throws SQLException{
        LocalDate date= LocalDate.now();
        ReservationService.addReservation("ioana", Date.valueOf(date), Date.valueOf(date), "waiting", "Single Room", true, true, true, 500);
        ReservationService.addReservation("ioana", Date.valueOf(date), Date.valueOf(date), "waiting", "Single Room", true, true, true, 500);

        ResultSet reservations = ReservationService.getClientReservations("ioana");
        int cont = 0;
        while(reservations.next()){
            cont++;
        }
        Assertions.assertEquals(cont, 2);
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from reservations");
        statement.executeUpdate();
    }





}
