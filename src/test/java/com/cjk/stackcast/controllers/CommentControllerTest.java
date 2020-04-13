package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.services.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;
    //***************************************************  Show Comment By Id Found  ***********************************
    @Test
    @DisplayName("Get /comments/showComment/1 - Found")
    public void testShowCommentFound() throws Exception{
        Comment mockComment = new Comment(1L,1L , 1L,"Test Comment");
        doReturn(mockComment).when(commentService).create(mockComment);
        doReturn(Optional.of(mockComment)).when(commentService).showComment(1L);

        mockMvc.perform(get("/zc-video-app/comments/showComment/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commentId",is(1)))
                .andExpect(jsonPath("$.videoId",is(1)))
                .andExpect(jsonPath("$.userId",is(1)))
                .andExpect(jsonPath("$.comment",is("Test Comment")));
    }

    //***************************************************  Show Comment By Id Not Found  *******************************
    @Test
    @DisplayName("Get /comments/showComment/1 - Not Found")
    public void testShowCommentNotFound() throws Exception{

        doReturn(Optional.empty()).when(commentService).showComment(1L);

        mockMvc.perform(get("/zc-video-app/comments/showComment/{id}",1))
                .andExpect(status().isNotFound());
    }

    //***************************************************  Show All Comments  ******************************************
    @Test
    @DisplayName("Get /comments/show - Found")
    public void testShowAllComments() throws Exception{
        Comment mockComment1 = new Comment(1L,1L , 1L,"Test Comment 1");
        Comment mockComment2 = new Comment(2L,2L , 2L,"Test Comment 2");

        Iterable<Comment> comments = new ArrayList<>(Arrays.asList(mockComment1,mockComment2));
        doReturn(comments).when(commentService).showAll();

        mockMvc.perform(get("/zc-video-app/comments/show",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].commentId",is(1)))
                .andExpect(jsonPath("$[0].videoId",is(1)))
                .andExpect(jsonPath("$[0].userId",is(1)))
                .andExpect(jsonPath("$[0].comment",is("Test Comment 1")))

                .andExpect(jsonPath("$[1].commentId",is(2)))
                .andExpect(jsonPath("$[1].videoId",is(2)))
                .andExpect(jsonPath("$[1].userId",is(2)))
                .andExpect(jsonPath("$[1].comment",is("Test Comment 2")));
    }
    @Test
    @DisplayName("Get /comments/create - Successful")
    public void testCreateComment() throws Exception{

        Comment mockComment = new Comment(1L , 1L,"Test Comment 1");

        given(commentService.create(mockComment)).willReturn(mockComment);
        mockMvc.perform(post("/zc-video-app/comments/create")
                .contentType(MediaType.APPLICATION_JSON));
    }
}