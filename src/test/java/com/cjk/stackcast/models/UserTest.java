package com.cjk.stackcast.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {

    User testUser = new User("BobSmith");

    @Test
    void getUserId(){
        Long expected = 1L;
        Long actual = testUser.getUserId();
        assertNotEquals(expected,actual);
    }

    @Test
    void setUserId(){
        Long expected = 1L;
        testUser.setUserId(expected);
        Long actual = testUser.getUserId();
        assertEquals(expected,actual);
    }

    @Test
    void getPasswordTest() {
        String expected = "";
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void setPasswordTest() {
        String expected = "testSample";
        testUser.setPassword(expected);
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void getUserNameTest() {
        String expected = "BobSmith";
        String actual = testUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void setUserNameTest() {
        String expected = "JeffTheGreat";
        testUser.setUserName(expected);
        String actual = testUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void dateCreatedTest() {
        LocalDate expected = LocalDate.now();
        LocalDate actual = testUser.getDateCreated();
        assertEquals(expected,actual);
    }

    @Test
    void setDateCreatedTest() {
        LocalDate expected = LocalDate.now();
        testUser.setDateCreated(expected);
        LocalDate actual = testUser.getDateCreated();
        assertEquals(expected,actual);
    }




}