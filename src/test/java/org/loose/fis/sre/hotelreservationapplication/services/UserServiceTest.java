package org.loose.fis.sre.hotelreservationapplication.services;

import org.junit.jupiter.api.*;
import org.loose.fis.sre.hotelreservationapplication.database.DBConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
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
        PreparedStatement statement;
        statement = DBConnection.connection.prepareStatement("DELETE from users");
        statement.executeUpdate();
    }

    @Test
    void encodePasswordTest() {
        String parola1 = "abc";
        String parola2 = "abc";
        Assertions.assertEquals(UserService.encodePassword(parola1), UserService.encodePassword(parola2));
    }

    @Test
    void validatePhoneNumberTest() {
        String phone1 = "012";
        String phone2 = "0123543645";
        Assertions.assertEquals(true, UserService.validatePhoneNumber(phone2));
        Assertions.assertEquals(false, UserService.validatePhoneNumber(phone1));
    }

    @Test
    void validateFullNameTest() {
        String name1 = "012";
        String name2 = "Sonia";
        Assertions.assertEquals(true, UserService.validateFullName(name2));
        Assertions.assertEquals(false, UserService.validateFullName(name1));
    }

}
