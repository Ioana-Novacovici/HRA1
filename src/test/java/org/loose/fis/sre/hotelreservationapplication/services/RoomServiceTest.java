package org.loose.fis.sre.hotelreservationapplication.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomServiceTest {

    @BeforeAll
    static void beforeAll(){
        DBConnection.connect("jdbc:mysql://localhost:3306/hoteladministrationtest");
    }

    @AfterAll
    static void afterAll(){
        DBConnection.close();
    }

    @Test
    void getRoomsTest() throws SQLException {
        ResultSet rooms = RoomService.getRooms();
        int cntRooms = 0;
        while(rooms.next()){
            cntRooms++;
        }
        Assertions.assertEquals(5, cntRooms);
    }

    @Test
    void getAvailableRoomsTest() throws SQLException {
        ResultSet rooms = RoomService.getAvailableRooms();
        int nrRooms = 0;
        while(rooms.next()){
            nrRooms += rooms.getInt(4);
        }
        Assertions.assertEquals(50, nrRooms);
    }

}
