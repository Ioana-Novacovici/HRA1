package org.loose.fis.sre.hotelreservationapplication.models;

import java.util.Objects;

public class Room {

    private String type;
    private int nrPers;
    private int price;
    private int nrRooms;
    private String img;

    public Room(String type, int nrPers, int price, int nrRooms, String img) {
        this.type = type;
        this.nrPers = nrPers;
        this.price = price;
        this.nrRooms = nrRooms;
        this.img = img;
    }

    public Room() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrPers() {
        return nrPers;
    }

    public void setNrPers(int nrPers) {
        this.nrPers = nrPers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNrRooms() {
        return nrRooms;
    }

    public void setNrRooms(int nrRooms) {
        this.nrRooms = nrRooms;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {this.img = img;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getNrPers() == room.getNrPers() && getPrice() == room.getPrice() && getNrRooms() == room.getNrRooms() && getType().equals(room.getType()) && getImg().equals(room.getImg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getNrPers(), getPrice(), getNrRooms(), getImg());
    }
}
