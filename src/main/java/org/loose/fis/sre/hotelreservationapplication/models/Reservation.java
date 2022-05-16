package org.loose.fis.sre.hotelreservationapplication.models;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private int idReservation;
    private String username;
    private Date startDate;
    private Date endDate;
    private String status;
    private String type;
    private boolean extraBed;
    private boolean breakfast;
    private boolean parking;
    private int price;

    public Reservation(int idReservation, String username, Date startDate, Date endDate, String status, String type, boolean extraBed, boolean breakfast, boolean parking, int price) {
        this.idReservation = idReservation;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.extraBed = extraBed;
        this.breakfast = breakfast;
        this.parking = parking;
        this.price = price;
    }

    public Reservation(){

    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return idReservation == that.idReservation && extraBed == that.extraBed && breakfast == that.breakfast && parking == that.parking && price == that.price && username.equals(that.username) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && status.equals(that.status) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, username, startDate, endDate, status, type, extraBed, breakfast, parking, price);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", username='" + username + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", extraBed=" + extraBed +
                ", breakfast=" + breakfast +
                ", parking=" + parking +
                ", price=" + price +
                '}';
    }
}


