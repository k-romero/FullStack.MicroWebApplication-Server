package com.cjk.stackcast.models;

import javax.persistence.*;

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
    @Column(name = "VIDEO_KEY")
    private String originalVideoKey;
    @Column(name = "USER_ID")
    private Long userId;

    public Video(String videoName, String videoPath) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoType = "";
        videoViews = 0;
        originalVideoKey = "";
    }

    public Video(Long videoId,String videoName, String videoPath, String videoType) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoType = videoType;
        this.videoViews = 0;
        this.originalVideoKey = "";
        this.userId = 0L;
    }

    public Video(String videoName, String videoType, String originalVideoKey) {
        this.videoName = videoName;
        this.videoType = videoType;
        this.originalVideoKey = originalVideoKey;
        videoViews = 0;
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
}
