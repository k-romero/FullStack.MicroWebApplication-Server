package com.cjk.stackcast.models.video;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicVideoTest {

    BasicVideo testVideo;

    @BeforeEach
    void setUp() {
        testVideo = new BasicVideo();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void instanceOf(){
        assertTrue(testVideo instanceof Video);
    }

    @Test
    void getVideoId() {
        assertNull(testVideo.getVideoId());
    }

    @Test
    void setVideoId() {
        Long expected = 1L;
        testVideo.setVideoId(expected);
        Long actual = testVideo.getVideoId();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoName() {
        assertNull(testVideo.getVideoName());
    }

    @Test
    void setVideoName() {
        String expected = "test-movie";
        testVideo.setVideoName(expected);
        String actual = testVideo.getVideoName();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoPath() {
        assertNull(testVideo.getVideoPath());
    }

    @Test
    void setVideoPath() {
        String expected = "https://zip-code-video-app.s3.amazonaws.com/test-video.mp4";
        testVideo.setVideoName(expected);
        String actual = testVideo.getVideoName();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoType() {
        assertNull(testVideo.getVideoType());
    }

    @Test
    void setVideoType() {
        String expected = "mp4";
        testVideo.setVideoName(expected);
        String actual = testVideo.getVideoName();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoViews() {
        assertNull(testVideo.getVideoViews());
    }

    @Test
    void setVideoViews() {
        Integer expected = 1;
        testVideo.setVideoViews(1);
        Integer actual = testVideo.getVideoViews();
        assertEquals(expected,actual);
    }
}