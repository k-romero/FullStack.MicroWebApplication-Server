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

    @Test
    void getConnectionStatus(){
        assertFalse(testUser.getIsConnected());
    }

    @Test
    void setConnectionStatus(){
        testUser.setIsConnected(true);
        assertTrue(testUser.getIsConnected());
    }

    @Test
    void nullConstructorTest(){
        User user = new User();
        assertNull(user.getId());
    }

    @Test
    void constructor2ParamTest(){
        User testUser = new User("testUserName","testPassword");
        assertEquals("testUserName",testUser.getUserName());
        assertEquals("testPassword",testUser.getPassword());
    }

    @Test
    void constructor3ParamTest(){
        User testUser = new User(1L,"testUserName","testPassword");
        Long expectedId = 1L;
        Long actualId = testUser.getId();
        assertEquals(expectedId,actualId);
        assertEquals("testUserName",testUser.getUserName());
        assertEquals("testPassword",testUser.getPassword());
    }

    @Test
    void constructor4ParamTest(){
        User testUser = new User(1L,"testUserName","testPassword",LocalDate.now(),true);
        Long expectedId = 1L;
        Long actualId = testUser.getId();
        assertEquals(expectedId,actualId);
        assertEquals("testUserName",testUser.getUserName());
        assertEquals("testPassword",testUser.getPassword());
        assertNotNull(testUser.getDateCreated());
        assertTrue(testUser.getIsConnected());
    }


}