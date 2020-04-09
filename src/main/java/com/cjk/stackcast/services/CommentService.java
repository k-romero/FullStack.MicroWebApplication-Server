package com.cjk.stackcast.services;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    public Iterable<Comment> showAll(){
        return commentRepo.findAll();
    }

    public Comment create(Comment comment){
        return commentRepo.save(comment);
    }

    public Boolean deleteComment(Long commentId){
        Comment comment = commentRepo.getOne(commentId);
        if(comment.getCommentId().equals(commentId)){
            commentRepo.deleteById(commentId);
            return true;
        }else{
            return false;

        }
    }

    public List<String> findByVideoId(Long videoId){
        List<String> comments = new ArrayList();
        //comentRopo.findCommentsByVideoId(videoId)
        for(Comment comment : commentRepo.findAll()){
            if(comment.getVideoId().equals(videoId)){
                comments.add(comment.getComment());
            }
        }
        return comments;
    }
}
