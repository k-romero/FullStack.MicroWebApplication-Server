package com.cjk.stackcast.models.video;

import javax.persistence.Entity;

@Entity
public class BasicVideo extends Video{

    public BasicVideo() {
    }

    public BasicVideo(String videoName){
        super(videoName);
    }

    public BasicVideo(String videoName, String videoPath){
        super(videoName,videoPath);
    }

    public BasicVideo(Long videoId, String videoName){
        super(videoId,videoName);
    }

    public BasicVideo(String videoName, String videoPath, String videoType){
        super(videoName,videoPath,videoType);
    }

    public BasicVideo(Long videoId, String videoName, String videoPath, String videoType){
        super(videoId,videoName,videoPath,videoType);
    }


}
