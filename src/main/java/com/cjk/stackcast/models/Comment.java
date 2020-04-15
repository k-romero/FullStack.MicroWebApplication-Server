package com.cjk.stackcast.models;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long commentId;
    private Long userId;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "VIDEO_ID")
    private Video video;


    public Comment(){
    }

    public Comment(Long commentID, Long userId , String comment){
        this.commentId = commentID;
        this.userId = userId;
        this.comment = comment;
    }

    public Comment(Long userId , String comment){
        this.userId = userId;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
