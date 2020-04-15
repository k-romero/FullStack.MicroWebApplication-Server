package com.cjk.stackcast.models;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long commentId;
    private Long userId;
    private String message;
    @ManyToOne
    @JoinColumn(name = "VIDEO_ID")
    private Video video;


    public Comment(){
    }

    public Comment(Long commentID, Long userId , String message){
        this.commentId = commentID;
        this.userId = userId;
        this.message = message;
    }

    public Comment(Long userId , String message){
        this.userId = userId;
        this.message = message;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
