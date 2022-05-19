package org.loose.fis.sre.hotelreservationapplication.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    @BeforeAll
    static void beforeAll(){
        DBConnection.connect("jdbc:mysql://localhost:3306/hoteladministrationtest");
    }

    @AfterAll
    static void afterAll(){
        DBConnection.close();
    }

    @Test
    void testUserAdded() throws SQLException {
        UserService.addUser("admin", "password", "role", "name", "1234567897");
        assertTrue(UserService.searchUser("admin", "password", "role"));
        assertEquals(4, 2 +2);

    }
}
