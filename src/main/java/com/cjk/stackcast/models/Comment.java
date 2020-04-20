package com.cjk.stackcast.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long commentId;
    private Long userId;
    private String username;
    private Date dateCreated;
    @NotEmpty(message = "Comment message cannot be empty!")
    private String message;
    @JsonIgnore
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
