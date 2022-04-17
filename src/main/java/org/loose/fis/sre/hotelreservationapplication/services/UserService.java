package org.loose.fis.sre.hotelreservationapplication.services;

import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public static void addUser(String username, String password, String role, String fullName, String phoneNumber) throws SQLException {

        Statement statement = DBConnection.connection.createStatement();
        statement.executeUpdate("INSERT INTO user (username, password, role, fullName, phoneNumber) VALUES ('" + username + "', '" + password + "', '" + role + "', '" + fullName + "', '" + phoneNumber + "');");
    }

}

