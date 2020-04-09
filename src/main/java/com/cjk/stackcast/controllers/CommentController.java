package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.comment.BasicComment;
import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.services.CommentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping( value = "zc-video-app/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    //********************************************************************  Show All   ******************
    @GetMapping("/show")
    public ResponseEntity<Iterable<Comment>> showAll(){
        return new ResponseEntity<>(service.showAll() , HttpStatus.OK);
    }

    //********************************************************************  Create   ********************
    @PostMapping("/create")
    public ResponseEntity<BasicComment> create(@RequestBody BasicComment comment){

        BasicComment newComment = this.service.create(comment);
        try {
            return ResponseEntity
                    .created( new URI("/create" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //********************************************************************  Remove   ******************
    @DeleteMapping(value ="/delete/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(service.deleteComment(commentId) , HttpStatus.OK);
    }

    //********************************************************************  Find By Video ID   ***********
    @GetMapping("/findByVideoId/{videoId}")
    public ResponseEntity<List<String>> findComentsByVideoId(@PathVariable Long videoId){
        return new ResponseEntity<>(service.findByVideoId(videoId) , HttpStatus.OK);
    }
}
