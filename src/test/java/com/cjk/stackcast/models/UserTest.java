package com.cjk.stackcast.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {

    User testUser = new User("BobSmith");


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
    void getFirstNameTest() {
        String expected = "BobSmith";
        String actual = testUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void setFirstNameTest() {
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
        assertNotNull(testUser.getDateCreated());
        System.out.println(testUser.getDateCreated());
    }




}