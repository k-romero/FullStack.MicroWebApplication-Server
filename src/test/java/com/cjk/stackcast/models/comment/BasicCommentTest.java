package com.cjk.stackcast.models.comment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicCommentTest {

    BasicComment comment;

    @Before
    public void setUp() throws Exception {
        comment = new BasicComment();
    }

    @After
    public void tearDown() throws Exception {
        comment = null;
    }

    @Test
    public void instanceOfComment(){
        Assert.assertTrue(comment instanceof Comment);
    }
    @Test
    public void nullaryConstructor(){
        Assert.assertNull(comment.getComment());
    }
    @Test
    public void constructorWithThreeParams(){

        Long videoId = Long.valueOf(1001);
        Long userId = Long.valueOf(0000);
        String actualComment = "Comment for testing";

        comment = new BasicComment(videoId , userId , actualComment);

        Assert.assertEquals(videoId , comment.getVideoId());
        Assert.assertEquals(userId , comment.getUserId());
        Assert.assertEquals(actualComment , comment.getComment());
    }
    @Test
    public void constructorWithTwoParams(){
        Long videoId = Long.valueOf(1001);
        String actualComment = "Comment for testing";

        comment = new BasicComment(videoId , actualComment);

        Assert.assertEquals(videoId , comment.getVideoId());
        Assert.assertEquals(actualComment , comment.getComment());
    }
    @Test
    public void setCommentID(){

        Assert.assertNull(comment.getCommentId());
        comment.setCommentId(Long.valueOf(1));
        Assert.assertEquals(Long.valueOf(1) , comment.getCommentId());
    }
    @Test
    public void setVideoID(){

        Assert.assertNull(comment.getCommentId());
        comment.setVideoId(Long.valueOf(1001));
        Assert.assertEquals(Long.valueOf(1001) , comment.getVideoId());
    }
    @Test
    public void setUserID(){

        Assert.assertNull(comment.getCommentId());
        comment.setUserId(Long.valueOf(1001));
        Assert.assertEquals(Long.valueOf(1001) , comment.getUserId());
    }
    @Test
    public void setComment(){

        Assert.assertNull(comment.getCommentId());
        comment.setComment("First Comment");
        Assert.assertEquals("First Comment" , comment.getComment());
    }


}