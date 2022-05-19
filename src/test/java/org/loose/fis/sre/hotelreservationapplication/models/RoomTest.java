package org.loose.fis.sre.hotelreservationapplication.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {
    @Test
    public void noArgsConstructor() {
        Room room = new Room();
        Assertions.assertNotNull(room);
        Assertions.assertNull(room.getType());
        Assertions.assertNull(room.getImg());
        Assertions.assertEquals(0, room.getPrice());
        Assertions.assertEquals(0, room.getNrRooms());
        Assertions.assertEquals(0, room.getNrPers());
    }

    @Test
    public void Contructor() {
        Room room = new Room("type", 3, 300, 5, "img");
        Assertions.assertNotNull(room);
        Assertions.assertEquals(room.getType(), "type");
        Assertions.assertEquals(room.getNrPers(), 3);
        Assertions.assertEquals(room.getPrice(), 300);
        Assertions.assertEquals(room.getNrRooms(), 5);
        Assertions.assertEquals(room.getImg(), "img");
    }

    @Test
    public void getTypeTest() {
        Room room = new Room();
        room.setType("type");
        Assertions.assertEquals(room.getType(), "type");
    }

    @Test
    public void getPersTest() {
        Room room = new Room();
        room.setNrPers(3);
        Assertions.assertEquals(room.getNrPers(), 3);
    }

    @Test
    public void getPriceTest() {
        Room room = new Room();
        room.setPrice(300);
        Assertions.assertEquals(room.getPrice(), 300);
    }

    @Test
    public void getRoomsTest() {
        Room room = new Room();
        room.setNrRooms(6);
        Assertions.assertEquals(room.getNrRooms(), 6);
    }

    @Test
    public void getImgTest() {
        Room room = new Room();
        room.setImg("img");
        Assertions.assertEquals(room.getImg(), "img");
    }

    @Test
    public void setUsernameTest() {
        Room room = new Room("type", 3, 300, 5, "img");
        room.setType("newType");
        Assertions.assertEquals(room.getType(), "newType");
    }

    @Test
    public void setPersTest() {
        Room room = new Room("type", 3, 300, 5, "img");
        room.setNrPers(4);
        Assertions.assertEquals(room.getNrPers(), 4);
    }

    @Test
    public void setPriceTest() {
        Room room = new Room("type", 3, 300, 5, "img");
        room.setPrice(400);
        Assertions.assertEquals(room.getPrice(), 400);
    }

    @Test
    public void setRoomsTest() {
        Room room = new Room("type", 3, 300, 5, "img");
        room.setNrRooms(7);
        Assertions.assertEquals(room.getNrRooms(), 7);
    }

    @Test
    public void setImgTest() {
        Room room = new Room("type", 3, 300, 5, "img");
        room.setImg("newImg");
        Assertions.assertEquals(room.getImg(), "newImg");
    }

    @Test
    public void equalsTest() {
        Room room1 = new Room("type", 3, 300, 5, "img");
        Room room2 = new Room("type", 3, 300, 5, "img");
        Room room3 = new Room("type", 5, 300, 5, "img");
        Assertions.assertEquals(true, room1.equals(room2));
        Assertions.assertEquals(false, room1.equals(room3));
    }

    @Test
    public void hashCodeTest() {
        Room room1 = new Room("type", 3, 300, 5, "img");
        Room room2 = new Room("type", 3, 300, 5, "img");
        Assertions.assertEquals(room1.hashCode(), room2.hashCode());
    }

}
