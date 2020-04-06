package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.services.CommentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "zc-video-app/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public String findComentsByVideoId(@RequestParam Long videoId){
        return "here";
    }


}
