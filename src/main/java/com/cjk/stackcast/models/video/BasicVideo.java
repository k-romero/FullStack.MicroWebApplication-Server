package com.cjk.stackcast.models.video;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BasicVideo")
public class BasicVideo extends Video{

    public BasicVideo() {
    }

    public BasicVideo(Long videoId,String videoName, String videoPath, String videoType){
        super(videoId,videoName,videoPath,videoType);
    }

    public BasicVideo(String videoName, String videoPath, String videoType){
        super(videoName,videoPath,videoType);
    }

    public BasicVideo(String videoName, String videoType){
        super(videoName,videoType);
    }

}
