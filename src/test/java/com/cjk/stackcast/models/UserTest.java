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

    User testUser = new User(1L,"Bob","Smith");


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
    void getUserIdTest() {
        Long expected = (1L);
        Long actual = testUser.getUserId();
        assertEquals(expected,actual);
    }

    @Test
    void setUserIdTest() {
        Long expected = 2L;
        testUser.setUserId(expected);
        Long actual = testUser.getUserId();
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
    void commentHistoryTest() {
        //Comment comment = new Comment();
        // The comment class has not be created yet
    }

    @Test
    void uploadHistoryTest() {
        ArrayList<Long> expected = new ArrayList();
        expected.add(1L);
        testUser.setUploadHistory(expected);
        ArrayList<Long>actual = testUser.getUploadHistory();
        assertEquals(expected, actual);
    }

    @Test
    void dateCreatedTest() {
        LocalDate expected = LocalDate.now();
        LocalDate actual = testUser.getDateCreated();
        assertEquals(expected,actual);
    }
        // Do we need a local date if when the user being created the date ia actually the date of the current date, which is now?
        // Do the date created need to be a "final"?
    @Test
    void setDateCreatedTest() {
    }




}