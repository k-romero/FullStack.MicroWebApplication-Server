package com.cjk.stackcast.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIDEO_ID")
    private Long videoId;
    @Column(name = "VIDEO_NAME")
    private String videoName;
    @Column(name = "VIDEO_PATH")
    private String videoPath;
    @Column(name = "VIDEO_TYPE")
    private String videoType;
    @Column(name = "VIDEO_VIEWS")
    private Integer videoViews;
    @Column(name = "LIKES")
    private Integer likes;
    @Column(name = "DISLIKES")
    private Integer dislikes;
    @Column(name = "VIDEO_KEY")
    private String originalVideoKey;
    @Column(name = "USER_ID")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "video")
    List<Comment> comments;

    public Video(String videoName, String videoPath) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoType = "";
        videoViews = 0;
        this.likes = 0;
        this.dislikes = 0;
        originalVideoKey = "";
    }

    public Video(Long videoId,String videoName, String videoPath, String videoType) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoType = videoType;
        this.videoViews = 0;
        this.likes = 0;
        this.dislikes = 0;
        this.originalVideoKey = "";
        this.userId = 0L;
        this.comments = new ArrayList<>();
    }

    public Video(String videoName, String videoType, String originalVideoKey) {
        this.videoName = videoName;
        this.videoType = videoType;
        this.originalVideoKey = originalVideoKey;
        videoViews = 0;
        this.likes = 0;
        this.dislikes = 0;
        videoPath = "";
    }

    public Video() {
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Integer getVideoViews() {
        return videoViews;
    }

    public void setVideoViews(Integer videoViews) {
        this.videoViews = videoViews;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOriginalVideoKey() {
        return originalVideoKey;
    }

    public void setOriginalVideoKey(String originalVideoKey) {
        this.originalVideoKey = originalVideoKey;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoType='" + videoType + '\'' +
                ", videoViews=" + videoViews +
                ", originalVideoKey='" + originalVideoKey + '\'' +
                ", userId=" + userId +
                ", comments=" + comments +
                '}';
    }
}
