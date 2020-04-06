package com.cjk.stackcast.services;

import com.cjk.stackcast.models.comment.BasicComment;
import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentService {

    @Autowired
    private CommentRepository comentRopo;

    public Comment create(Comment comment){
        return comentRopo.save(comment);
    }
    public Iterable<Comment> showAll(){
        return comentRopo.findAll();
    }



}
