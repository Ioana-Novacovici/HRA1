package org.loose.fis.sre.hotelreservationapplication.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ReservationTest {
    @Test
    public void noArgsConstructor() {
        Reservation reservation = new Reservation();
        Assertions.assertNotNull(reservation);
        Assertions.assertNull(reservation.getType());
        Assertions.assertNull(reservation.getStatus());
        Assertions.assertNull(reservation.getUsername());
        Assertions.assertEquals(0, reservation.getPrice());
        Assertions.assertEquals(0, reservation.getIdReservation());
        Assertions.assertEquals(false, reservation.isBreakfast());
        Assertions.assertEquals(false, reservation.isExtraBed());
        Assertions.assertEquals(false, reservation.isParking());
        Assertions.assertNull(reservation.getEndDate());
        Assertions.assertNull(reservation.getStartDate());
    }

    @Test
    public void Contructor() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Assertions.assertNotNull(reservation);
        Assertions.assertEquals("doubleRoom", reservation.getType());
        Assertions.assertEquals("status", reservation.getStatus());
        Assertions.assertEquals("user", reservation.getUsername());
        Assertions.assertEquals(200, reservation.getPrice());
        Assertions.assertEquals(1, reservation.getIdReservation());
        Assertions.assertEquals(false, reservation.isBreakfast());
        Assertions.assertEquals(true, reservation.isExtraBed());
        Assertions.assertEquals(true, reservation.isParking());
        Assertions.assertEquals(d2, reservation.getEndDate());
        Assertions.assertEquals(d1, reservation.getStartDate());
    }

    @Test
    public void getIdTest() {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(1);
        Assertions.assertEquals(reservation.getIdReservation(), 1);
    }

    @Test
    public void getUsernameTest() {
        Reservation reservation = new Reservation();
        reservation.setUsername("user");
        Assertions.assertEquals(reservation.getUsername(), "user");
    }

    @Test
    public void getStatusTest() {
        Reservation reservation = new Reservation();
        reservation.setStatus("status");
        Assertions.assertEquals(reservation.getStatus(), "status");
    }

    @Test
    public void getTypeTest() {
        Reservation reservation = new Reservation();
        reservation.setType("type");
        Assertions.assertEquals(reservation.getType(), "type");
    }

    @Test
    public void getDate1Test() {
        Date d1 = new Date(2022, 11, 11);
        Reservation reservation = new Reservation();
        reservation.setStartDate(d1);
        Assertions.assertEquals(reservation.getStartDate(), d1);
    }

    @Test
    public void getDate2Test() {
        Date d2 = new Date(2022, 11, 11);
        Reservation reservation = new Reservation();
        reservation.setEndDate(d2);
        Assertions.assertEquals(reservation.getEndDate(), d2);
    }

    @Test
    public void getBreakfastTest() {
        Reservation reservation = new Reservation();
        reservation.setBreakfast(true);
        Assertions.assertEquals(reservation.isBreakfast(), true);
    }

    @Test
    public void getExtraBedTest() {
        Reservation reservation = new Reservation();
        reservation.setExtraBed(false);
        Assertions.assertEquals(reservation.isExtraBed(), false);
    }

    @Test
    public void getParkingTest() {
        Reservation reservation = new Reservation();
        reservation.setParking(false);
        Assertions.assertEquals(reservation.isParking(), false);
    }

    @Test
    public void getPriceTest() {
        Reservation reservation = new Reservation();
        reservation.setPrice(100);
        Assertions.assertEquals(reservation.getPrice(), 100);
    }

    @Test
    public void setIdTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setIdReservation(2);
        Assertions.assertEquals(reservation.getIdReservation(), 2);
    }

    @Test
    public void setDate1Test() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setStartDate(d2);
        Assertions.assertEquals(reservation.getStartDate(), d2);
    }

    @Test
    public void setDate2Test() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setEndDate(d1);
        Assertions.assertEquals(reservation.getEndDate(), d1);
    }

    @Test
    public void setUsernameTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setUsername("sonia");
        Assertions.assertEquals(reservation.getUsername(), "sonia");
    }

    @Test
    public void setTypeTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setType("single");
        Assertions.assertEquals(reservation.getType(), "single");
    }

    @Test
    public void setStatusTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setStatus("accepted");
        Assertions.assertEquals(reservation.getStatus(), "accepted");
    }

    @Test
    public void setBreakfastTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setBreakfast(true);
        Assertions.assertEquals(reservation.isBreakfast(), true);
    }

    @Test
    public void setParkingTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setParking(false);
        Assertions.assertEquals(reservation.isParking(), false);
    }

    @Test
    public void setExtraBedTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setExtraBed(false);
        Assertions.assertEquals(reservation.isExtraBed(), false);
    }

    @Test
    public void setPriceTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        reservation.setPrice(500);
        Assertions.assertEquals(reservation.getPrice(), 500);
    }

    @Test
    public void equalsTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation1 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Reservation reservation2 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Assertions.assertEquals(true, reservation1.equals(reservation2));
        Assertions.assertEquals(true, reservation2.equals(reservation1));
    }

    @Test
    public void hashCodeTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation1 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Reservation reservation2 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Assertions.assertEquals(reservation1.hashCode(), reservation2.hashCode());
    }

    @Test
    public void toStringTest() {
        Date d1 = new Date(2022, 11, 11);
        Date d2 = new Date(2022, 11, 12);
        Reservation reservation1 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Reservation reservation2 = new Reservation(1, "user", d1, d2, "status", "doubleRoom", true, false, true, 200);
        Assertions.assertEquals(reservation1.toString(), reservation2.toString());
    }
}
