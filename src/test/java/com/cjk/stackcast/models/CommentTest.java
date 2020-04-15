package com.cjk.stackcast.models;

import com.cjk.stackcast.models.Comment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommentTest {

    Comment comment;

    @Before
    public void setUp() throws Exception {
        comment = new Comment();
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
    public void nullConstructor(){
        Assert.assertNull(comment.getComment());
    }


    @Test
    public void setCommentID(){

        Assert.assertNull(comment.getCommentId());
        comment.setCommentId(Long.valueOf(1));
        Assert.assertEquals(Long.valueOf(1) , comment.getCommentId());
    }

    @Test
    public void setUserID(){

        Assert.assertNull(comment.getCommentId());
        comment.setUserId(Long.valueOf(1001));
        Assert.assertEquals(Long.valueOf(1001) , comment.getUserId());
    }
    @Test
    public void setComment() {

        Assert.assertNull(comment.getCommentId());
        comment.setComment("First Comment");
        Assert.assertEquals("First Comment", comment.getComment());
    }


}