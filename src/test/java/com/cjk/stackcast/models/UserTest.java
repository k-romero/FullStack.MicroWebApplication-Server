package com.cjk.stackcast.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class UserTest {

    User testUser = new User("BobSmith");

    @Test
    void getUserId(){
        Long expected = 1L;
        Long actual = testUser.getId();
        assertNotEquals(expected,actual);
    }

    @Test
    void setUserId(){
        Long expected = 1L;
        testUser.setId(expected);
        Long actual = testUser.getId();
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

    @Test
    void userVideosTest() {
        Video video1 = new Video();
        Video video2 = new Video();
        Video video3 = new Video();
        Video[] videos = new Video[]{video1,video2,video3};
        testUser.setUserVideos(Arrays.asList(videos));

        int expected = 3;
        int actual = testUser.getUserVideos().size();

        assertEquals(expected,actual);
    }





}