package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomService {

    public static ResultSet getRooms () throws SQLException {

        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("SELECT * from rooms ORDER BY nrPers");
        ResultSet rooms = statement.executeQuery();
        return rooms;
    }

}
