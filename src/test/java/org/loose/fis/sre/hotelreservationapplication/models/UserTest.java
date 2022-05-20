package org.loose.fis.sre.hotelreservationapplication.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void noArgsConstructor() {
        User user = new User();
        Assertions.assertNotNull(user);
        Assertions.assertNull(user.getUsername());
        Assertions.assertNull(user.getPassword());
        Assertions.assertNull(user.getRole());
        Assertions.assertNull(user.getFullName());
        Assertions.assertNull(user.getPhoneNumber());
    }

    @Test
    public void Constructor() {
        User user = new User("user", "password", "role", "fullName", "phone");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getUsername(), "user");
        Assertions.assertEquals(user.getPassword(), "password");
        Assertions.assertEquals(user.getRole(), "role");
        Assertions.assertEquals(user.getFullName(), "fullName");
        Assertions.assertEquals(user.getPhoneNumber(), "phone");
    }

    @Test
    public void getUsernameTest() {
        User user = new User();
        user.setUsername("user");
        Assertions.assertEquals(user.getUsername(), "user");
    }

    @Test
    public void getPasswordTest() {
        User user = new User();
        user.setPassword("password");
        Assertions.assertEquals(user.getPassword(), "password");
    }

    @Test
    public void getRoleTest() {
        User user = new User();
        user.setRole("role");
        Assertions.assertEquals(user.getRole(), "role");
    }

    @Test
    public void getFullNameTest() {
        User user = new User();
        user.setFullName("fullName");
        Assertions.assertEquals(user.getFullName(), "fullName");
    }

    @Test
    public void getPhoneTest() {
        User user = new User();
        user.setPhoneNumber("phone");
        Assertions.assertEquals(user.getPhoneNumber(), "phone");
    }

    @Test
    public void setUsernameTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        user.setUsername("newUsername");
        Assertions.assertEquals(user.getUsername(), "newUsername");
    }

    @Test
    public void setPasswordTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        user.setPassword("newPassword");
        Assertions.assertEquals(user.getPassword(), "newPassword");
    }

    @Test
    public void setRoleTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        user.setRole("newRole");
        Assertions.assertEquals(user.getRole(), "newRole");
    }

    @Test
    public void setFullNameTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        user.setFullName("newFullName");
        Assertions.assertEquals(user.getFullName(), "newFullName");
    }

    @Test
    public void setPhoneNumberTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        user.setPhoneNumber("newPhone");
        Assertions.assertEquals(user.getPhoneNumber(), "newPhone");
    }

    @Test
    public void equalsTest() {
        User user1 = new User("user", "password", "role", "fullName", "phone");
        User user2 = new User("user", "password", "role", "fullName", "phone");
        User user3 = new User("username", "parola", "rol", "fullName", "phone");
        Assertions.assertEquals(true, user1.equals(user2));
        Assertions.assertEquals(false, user1.equals(user3));
    }

    @Test
    public void hashCodeTest() {
        User user = new User("user", "password", "role", "fullName", "phone");
        int userHash = user.getUsername().hashCode();
        int passwordHash = user.getPassword().hashCode();
        int roleHash = user.getRole().hashCode();
        int result = userHash;
        result = 31 * result + passwordHash;
        result = 31 * result + roleHash;
        Assertions.assertEquals(result, user.hashCode());
    }
}
