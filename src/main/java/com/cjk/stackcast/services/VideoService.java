package com.cjk.stackcast.services;

import com.cjk.stackcast.controllers.AwsS3Controller;
import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repo;
    @Autowired
    private AwsS3Controller awsS3;

    public Optional<Video> show(Long videoId){
        return Optional.of(repo.findVideoByVideoId(videoId));
    }

    public Iterable<Video> showAll(){
        return repo.findAll();
    }

    public BasicVideo createBasicVideo(BasicVideo basicVideo){
        return repo.save(basicVideo);
    }

    public BasicVideo saveBasicVideo(String videoName, MultipartFile multipartFile) throws Exception{
        String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";

        //Convert file for upload
        File file = convertMultiPartFile(multipartFile);

        //Build video obj to store in db with url
        BasicVideo video = new BasicVideo(videoName,multipartFile.getContentType());
        String fileName = generateFileName(file.getName());
        String fileUrl = endPointUrl + "/" + fileName;
        video.setVideoPath(fileUrl);

        //Attempt to upload file and if OK store video body in DB
        if(this.awsS3.uploadFile(file,fileName).isSuccessful()){
           return createBasicVideo(video);
        } else
            return null;
    }

    public boolean delete(Long videoId){
        return repo.deleteVideoByVideoId(videoId);
    }



    //Covert MultiPart File
    public File convertMultiPartFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    //Generate Random File Name Prefix
    public String generateFileName(String fileName){
        return new Date().getTime() + "-" + fileName.replace(" ", "_");
    }









}
