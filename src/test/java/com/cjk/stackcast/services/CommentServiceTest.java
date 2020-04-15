package com.cjk.stackcast.services;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;


    @Test
    @DisplayName("Test showComment Success")
    public void testFindByIdFound(){
        Comment mockComment = new Comment(1L , "Test Comment");
        doReturn(Optional.of(mockComment)).when(commentRepository).findById(1L);

        Optional<Comment> resultComment = commentService.showComment(1L);

        Assertions.assertTrue(resultComment.isPresent());
        Assertions.assertSame(resultComment.get(),mockComment);
    }


    @Test
    @DisplayName("Test showComment Failure")
    public void testFindByIdNotFound(){

        doReturn(Optional.empty()).when(commentRepository).findById(1L);
        Optional<Comment> resultComment = commentService.showComment(1L);

        Assertions.assertFalse(resultComment.isPresent());
    }


    @Test
    @DisplayName("Test showAll")
    public void testShowAll(){
        // Set up mock object and repository
        Comment mockComment1 = new Comment(1L , "Test Comment 1");
        Comment mockComment2 = new Comment(2L , "Test Comment 2");

        doReturn(Arrays.asList(mockComment1,mockComment2)).when(commentRepository).findAll();
        List<Comment> commentsList = (List<Comment>) commentService.showAll();

        Assertions.assertEquals(2,commentsList.size());
    }


    @Test
    @DisplayName("Test create")
    public void testCreate(){

        Comment mockComment = new Comment(1L , "Test Comment 1");
        doReturn(mockComment).when(commentRepository).save(mockComment);

        Comment resultComment = commentService.create(mockComment);

        Assertions.assertNotNull(resultComment);
    }


    @Test
    @DisplayName("Test deleteComment Success")
    public void testDeleteComment(){

        Comment mockComment = new Comment(1L, 1L,"Test Comment 1");
        doReturn(mockComment).when(commentRepository).save(mockComment);

        doReturn(mockComment).when(commentRepository).getOne(1L);

        Boolean deleted = commentService.deleteComment(1L);

        Assertions.assertTrue(deleted);
    }

}