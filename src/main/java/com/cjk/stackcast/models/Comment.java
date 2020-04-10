package com.cjk.stackcast.models;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long commentId;
    private Long videoId;
    private Long userId;
    private String comment;


    public Comment(){
        this.commentId = null;
        this.videoId = null;
        this.userId = null;
        this.comment = null;

    }
    public Comment(Long commentId , Long videoId , Long userId , String comment){
        this.commentId = commentId;
        this.videoId = videoId;
        this.userId = userId;
        this.comment = comment;
    }

    public Comment(Long videoId , Long userId , String comment){
        this.videoId = videoId;
        this.userId = userId;
        this.comment = comment;
    }

    public Comment(Long videoId , String comment){
        this.videoId = videoId;
        this.userId = Long.valueOf(0000);
        this.comment = comment;
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




}
