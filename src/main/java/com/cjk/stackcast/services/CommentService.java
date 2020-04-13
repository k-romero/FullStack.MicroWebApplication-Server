package com.cjk.stackcast.services;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CommentService {


    private CommentRepository commentRepo;

    @Autowired
    public CommentService(CommentRepository commentRepo){
        this.commentRepo = commentRepo;
    }

    public Optional<Comment> showComment(Long id){
        return commentRepo.findById(id);
    }

    public Iterable<Comment> showAll(){
        return commentRepo.findAll();
    }

    public Comment create(Comment comment){
        return commentRepo.save(comment);
    }

    public Boolean deleteComment(Long commentId){
        Comment comment = commentRepo.getOne(commentId);
        if(commentId.equals(comment.getCommentId())){
            commentRepo.deleteById(commentId);
            return true;
        }else{
            return false;
        }
    }

    public List<String> findByVideoId(Long videoId){
        List<String> comments = new ArrayList<>();
        List<Comment> found = commentRepo.findAll();
        for(Comment comment : found){
            if(comment.getVideoId().equals(videoId)){
                comments.add(comment.getComment());
            }
        }
        return comments;
    }
}
