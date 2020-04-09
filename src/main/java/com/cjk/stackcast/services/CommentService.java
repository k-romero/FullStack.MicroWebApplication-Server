package com.cjk.stackcast.services;

import com.cjk.stackcast.models.comment.BasicComment;
import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CommentService {

    @Autowired
    private CommentRepository comentRopo;

    public Iterable<Comment> showAll(){
        return comentRopo.findAll();
    }
    public BasicComment create(BasicComment comment){
        return comentRopo.save(comment);
    }
    public Boolean deleteComment(Long commentId){
        Comment comment = comentRopo.getOne(commentId);
        if(comment.getCommentId().equals(commentId)){
            comentRopo.deleteById(commentId);
            return true;
        }else{
            return false;

        }
    }
    public List<String> findByVideoId(Long videoId){
        List<String> comments = new ArrayList();
        //comentRopo.findCommentsByVideoId(videoId)
        for(Comment comment : comentRopo.findAll()){
            if(comment.getVideoId().equals(videoId)){
                comments.add(comment.getComment());
            }
        }
        return comments;
    }
}
