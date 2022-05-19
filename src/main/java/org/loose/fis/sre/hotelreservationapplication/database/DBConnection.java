package org.loose.fis.sre.hotelreservationapplication.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connection;

    public static void connect(String DBpath){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DBpath, "hotel", "parola"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

