package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.services.CommentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping( value = "zc-video-app/comments")
public class CommentController {

    @Autowired
    private CommentService service;


    @PostMapping("/create")
    public ResponseEntity<Comment> create(@RequestBody Comment comment){

        Comment newComment = this.service.create(comment);
        try {
            return ResponseEntity
                    .created( new URI("/create" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/show")
    public ResponseEntity<Iterable<Comment>> showAll(){
        return new ResponseEntity<>(service.showAll() , HttpStatus.OK);
    }

    @GetMapping
    public String findComentsByVideoId(@RequestParam Long videoId){
        return "here";
    }


}
