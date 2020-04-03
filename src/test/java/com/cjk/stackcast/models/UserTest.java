package com.cjk.stackcast.models;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserTest {

    User testUser = new User(1L,"Bob","Smith");


    @Test
    void getPassword() {
        String expected = "";
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void setPassword() {
        String expected = "testSample";
        testUser.setPassword(expected);
        String actual = testUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void getUserId() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void setFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void getCommentHistory() {
    }

    @Test
    void getUploadHistory() {
    }

    @Test
    void getDateCreated() {
    }

    @Test
    void setDateCreated() {
    }

    @Test
    void setCommentHistory() {
    }

    @Test
    void setUploadHistory() {
    }
}