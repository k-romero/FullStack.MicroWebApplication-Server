package com.cjk.stackcast.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DAOUserTest {

    DAOUser testDAOUser = new DAOUser("BobSmith");

    @Test
    void getUserId(){
        Long expected = 1L;
        Long actual = testDAOUser.getId();
        assertNotEquals(expected,actual);
    }

    @Test
    void setUserId(){
        Long expected = 1L;
        testDAOUser.setId(expected);
        Long actual = testDAOUser.getId();
        assertEquals(expected,actual);
    }

    @Test
    void getPasswordTest() {
        String expected = "";
        String actual = testDAOUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void setPasswordTest() {
        String expected = "testSample";
        testDAOUser.setPassword(expected);
        String actual = testDAOUser.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    void getUserNameTest() {
        String expected = "BobSmith";
        String actual = testDAOUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void setUserNameTest() {
        String expected = "JeffTheGreat";
        testDAOUser.setUserName(expected);
        String actual = testDAOUser.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    void dateCreatedTest() {
        LocalDate expected = LocalDate.now();
        LocalDate actual = testDAOUser.getDateCreated();
        assertEquals(expected,actual);
    }

    @Test
    void setDateCreatedTest() {
        LocalDate expected = LocalDate.now();
        testDAOUser.setDateCreated(expected);
        LocalDate actual = testDAOUser.getDateCreated();
        assertEquals(expected,actual);
    }

    @Test
    void userVideosTest() {
        Video video1 = new Video();
        Video video2 = new Video();
        Video video3 = new Video();
        Video[] videos = new Video[]{video1,video2,video3};
        testDAOUser.setUserVideos(Arrays.asList(videos));
        int expected = 3;
        int actual = testDAOUser.getUserVideos().size();
        assertEquals(expected,actual);
    }

    @Test
    void getConnectionStatus(){
        assertFalse(testDAOUser.getIsConnected());
    }

    @Test
    void setConnectionStatus(){
        testDAOUser.setIsConnected(true);
        assertTrue(testDAOUser.getIsConnected());
    }

    @Test
    void nullConstructorTest(){
        DAOUser DAOUser = new DAOUser();
        assertNull(DAOUser.getId());
    }

    @Test
    void constructor2ParamTest(){
        DAOUser testDAOUser = new DAOUser("testUserName","testPassword");
        assertEquals("testUserName", testDAOUser.getUserName());
        assertEquals("testPassword", testDAOUser.getPassword());
    }

    @Test
    void constructor3ParamTest(){
        DAOUser testDAOUser = new DAOUser(1L,"testUserName","testPassword");
        Long expectedId = 1L;
        Long actualId = testDAOUser.getId();
        assertEquals(expectedId,actualId);
        assertEquals("testUserName", testDAOUser.getUserName());
        assertEquals("testPassword", testDAOUser.getPassword());
    }

    @Test
    void constructor4ParamTest(){
        DAOUser testDAOUser = new DAOUser(1L,"testUserName","testPassword",LocalDate.now(),true);
        Long expectedId = 1L;
        Long actualId = testDAOUser.getId();
        assertEquals(expectedId,actualId);
        assertEquals("testUserName", testDAOUser.getUserName());
        assertEquals("testPassword", testDAOUser.getPassword());
        assertNotNull(testDAOUser.getDateCreated());
        assertTrue(testDAOUser.getIsConnected());
    }


}