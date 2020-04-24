package com.cjk.stackcast.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    UserDTO testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserDTO("userName","password");
    }

    @Test
    void getUserName() {
        String expected = "userName";
        String actual = testUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void setUserName() {
        String expected = "newUserName";
        testUser.setUserName(expected);
        String actual = testUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void getPassword() {
        String expected = "password";
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void setPassword() {
        String expected = "newPassWord";
        testUser.setPassword(expected);
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void testToString() {
        String expected = "UserDTO{userName='userName', password='password'}";
        String actual = testUser.toString();
        assertEquals(expected,actual);
    }
}