package com.cjk.stackcast.services;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.models.Video;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class CommentService {


    private CommentRepository commentRepo;

    private VideoService videoService;

    @Autowired
    public CommentService(CommentRepository commentRepo, VideoService videoService){
        this.commentRepo = commentRepo;
        this.videoService = videoService;
    }

    public Optional<Comment> showComment(Long id){
        return commentRepo.findById(id);
    }

    public Iterable<Comment> showAll(){
        return commentRepo.findAll();
    }

    public List<Comment> findAllCommentsByVideoId(Long videoId){
        return commentRepo.findByVideo_VideoId(videoId);
    }

    public Comment create(Long videoId, Comment comment) throws Exception {
        Optional<Video> foundVideo = videoService.show(videoId);
        if(foundVideo.isPresent()){
            Comment baseComment = comment;
            baseComment.setDateCreated(new Date());
            Comment commentToAdd = commentRepo.save(baseComment);
            Video videoToSave = foundVideo.get();
            commentToAdd.setVideo(videoToSave);
            videoToSave.getComments().add(commentToAdd);
            videoService.saveVideo(videoToSave);
            return commentRepo.save(commentToAdd);
        } else throw new Exception("No video with that id was found!");
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

    public Comment updateCommentMessage(Long commentId, String newMessage) throws Exception {
        Optional<Comment> foundComment = commentRepo.findById(commentId);
        if(foundComment.isPresent()){
            foundComment.get().setMessage(newMessage);
            return commentRepo.save(foundComment.get());
        } else throw new Exception("No Comment with that id was found!");
    }

}
