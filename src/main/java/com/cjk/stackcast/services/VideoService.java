package com.cjk.stackcast.services;

import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.UserVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repo;

    public Optional<Video> show(Long videoId){
        return Optional.of(repo.findVideoByVideoId(videoId));
    }

    public Iterable<Video> showAll(){
        return repo.findAll();
    }

    public BasicVideo createBasicVideo(BasicVideo basicVideo){
        return repo.save(basicVideo);
    }

    public Video createUserVideo(UserVideo userVideo){
        return repo.save(userVideo);
    }

    public boolean delete(Long videoId){
        return repo.deleteVideoByVideoId(videoId);
    }










}
