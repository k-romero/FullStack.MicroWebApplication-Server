package com.cjk.stackcast.models.comment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCommentTest {

    UserComment comment;
    @Before
    public void setUp() throws Exception {
        comment = new UserComment();
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
    public void constructorWithAllParams(){

        Long videoId = Long.valueOf(1001);
        Long userId = Long.valueOf(0000);
        String actualComment = "Comment for testing";

        comment = new UserComment(videoId , userId , actualComment);

        Assert.assertEquals(videoId , comment.getVideoId());
        Assert.assertEquals(userId , comment.getUserId());
        Assert.assertEquals(actualComment , comment.getComment());
    }

}