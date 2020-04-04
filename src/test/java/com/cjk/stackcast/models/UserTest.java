package com.cjk.stackcast.models;

import com.cjk.stackcast.models.comment.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    User testUser = new User("Bob","Smith");


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
        String expected = "Bob";
        String actual = testUser.getFirstName();
        assertEquals(expected,actual);
    }

    @Test
    void setFirstNameTest() {
        String expected = "Chris";
        testUser.setFirstName(expected);
        String actual = testUser.getFirstName();
        assertEquals(expected,actual);
    }

    @Test
    void getLastNameTest() {
        String expected = "Smith";
        String actual = testUser.getLastName();
        assertEquals(expected,actual);
    }

    @Test
    void setLastNameTest() {
        String expected = "Joe";
        testUser.setLastName(expected);
        String actual = testUser.getLastName();
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