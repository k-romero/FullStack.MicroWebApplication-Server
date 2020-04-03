package com.cjk.stackcast.models.video;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserVideoTest {

    UserVideo testVideo;

    @BeforeEach
    void setUp() {
        testVideo = new UserVideo();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void instanceOfVideo(){
        assertTrue(testVideo instanceof Video);
    }

    @Test
    void getUserId() {
        assertNull(testVideo.getUserId());
    }

    @Test
    void setUserId() {
        Long expected = 1L;
        testVideo.setUserId(1L);
        Long actual = testVideo.getUserId();
        assertEquals(expected,actual);
    }
}