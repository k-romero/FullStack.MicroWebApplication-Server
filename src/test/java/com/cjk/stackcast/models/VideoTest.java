package com.cjk.stackcast.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    Video testVideo;

    @BeforeEach
    void setUp() {
        testVideo = new Video();
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
        testVideo.setVideoPath(expected);
        String actual = testVideo.getVideoPath();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoType() {
        assertNull(testVideo.getVideoType());
    }

    @Test
    void setVideoType() {
        String expected = "mp4";
        testVideo.setVideoType(expected);
        String actual = testVideo.getVideoType();
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

    @Test
    void getUser() {
        assertNull(testVideo.getUserId());
    }

    @Test
    void setUser() {
        Long expected = 1L;
        testVideo.setUserId(expected);
        Long actual = testVideo.getUserId();
        assertEquals(expected,actual);
    }

    @Test
    void getVideoKey() {
        assertNull(testVideo.getOriginalVideoKey());
    }

    @Test
    void setVideoKey() {
        String expected = "video.mp4";
        testVideo.setOriginalVideoKey(expected);
        String actual = testVideo.getOriginalVideoKey();
        assertEquals(expected,actual);
    }

    @Test
    void getCommentsTest(){
        assertNull(testVideo.getComments());
    }

    @Test
    void setComments(){
        testVideo.setComments(Arrays.asList(new Comment(), new Comment(), new Comment()));
        assertEquals(3, testVideo.getComments().size());
    }

    @Test
    void constructor2ParamTest(){
        Video vidTest = new Video("Testing Video","http://testvideopath.test");
        assertEquals("Testing Video",vidTest.getVideoName());
        assertEquals("http://testvideopath.test",vidTest.getVideoPath());
    }

    @Test
    void constructor3ParamTest(){
        Video vidTest = new Video("Testing Video","video/mp4","testvideo.mp4");
        Long expectedId = vidTest.getVideoId();
        assertEquals(expectedId,vidTest.getVideoId());
        assertEquals("Testing Video",vidTest.getVideoName());
        assertEquals("video/mp4",vidTest.getVideoType());
        assertEquals("testvideo.mp4",vidTest.getOriginalVideoKey());
    }

    @Test
    void constructor4ParamTest(){
        Video vidTest = new Video(1L,"Testing Video","http://testvideopath.test","video/mp4");
        Long expectedId = vidTest.getVideoId();
        assertEquals(expectedId,vidTest.getVideoId());
        assertEquals("Testing Video",vidTest.getVideoName());
        assertEquals("http://testvideopath.test",vidTest.getVideoPath());
        assertEquals("video/mp4",vidTest.getVideoType());
    }
}