package com.cjk.stackcast.models.video;

import com.cjk.stackcast.models.comment.Comment;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "discriminatorColumn")
@Table(name="Video")
public abstract class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;
    private String videoName;
    private String videoPath;
    private String videoType;
    private Integer videoViews;

    public Video(String videoName, String videoPath, String videoType) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoType = videoType;
        videoViews = 0;
    }

    public Video(String videoName, String videoType) {
        this.videoName = videoName;
        videoPath = "";
        this.videoType = videoType;
        videoViews = 0;
    }

    public Video() {
    }

    public Video(Long videoId, String videoName, String videoPath, String videoType) {
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
}
