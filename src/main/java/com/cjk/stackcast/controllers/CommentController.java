package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.services.CommentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public String findComentById(@RequestParam Long id){
        return  "Comment Found";
    }
}
