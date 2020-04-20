package com.cjk.stackcast.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CommentTest {

    Comment comment;

    @Before
    public void setUp() {
        comment = new Comment();
    }

    @After
    public void tearDown() {
        comment = null;
    }

    @Test
    public void instanceOfComment(){
        assertTrue(comment instanceof Comment);
    }

    @Test
    public void nullConstructor(){
        assertNull(comment.getMessage());
    }

    @Test
    public void setCommentID(){
        assertNull(comment.getCommentId());
        comment.setCommentId(Long.valueOf(1));
        assertEquals(Long.valueOf(1) , comment.getCommentId());
    }

    @Test
    public void setUserID(){
        assertNull(comment.getCommentId());
        comment.setUserId(Long.valueOf(1001));
        assertEquals(Long.valueOf(1001) , comment.getUserId());
    }

    @Test
    public void setMessage() {
        assertNull(comment.getCommentId());
        comment.setMessage("First Comment");
        assertEquals("First Comment", comment.getMessage());
    }

    @Test
    public void userNameTests(){
        assertNull(comment.getUsername());
        comment.setUsername("TestUser");
        assertEquals("TestUser", comment.getUsername());
    }

    @Test
    public void dateTest() throws InterruptedException {
        assertNull(comment.getDateCreated());
        comment.setDateCreated(new Date());
        TimeUnit.SECONDS.sleep(1);
        Date test = new Date();
        assertTrue(test.getTime() > comment.getDateCreated().getTime());
    }

    @Test
    public void getMessage(){
        assertNull(comment.getVideo());
    }

    @Test
    public void setVideo(){
        Video video = new Video();
        comment.setVideo(video);
        assertNotNull(comment.getVideo());
    }

    @Test
    public void constructor2ParamTest(){
        Comment com = new Comment(1L, "Testing");
        Long expectedId = 1L;
        Long actualId = com.getUserId();
        String expectedMessage = "Testing";
        String actualMessage = com.getMessage();
        assertEquals(expectedId,actualId);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void constructor3ParamTest(){
        Comment com = new Comment(1L, 2L,"Testing");
        Long expectedId = 1L;
        Long actualId = com.getCommentId();
        Long expectedUserId = 2L;
        Long actualUserId = com.getUserId();
        String expectedMessage = "Testing";
        String actualMessage = com.getMessage();
        assertEquals(expectedId,actualId);
        assertEquals(expectedUserId,actualUserId);
        assertEquals(expectedMessage,actualMessage);
    }

}
