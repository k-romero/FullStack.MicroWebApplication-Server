package com.cjk.stackcast.controllers;

import com.cjk.stackcast.aws.AwsS3Configuration;
import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping(value = "/zc-video-app/videos")
public class VideoController {

    @Autowired
    private VideoService service;
    @Autowired
    private AwsS3Controller awsS3;

    @Autowired
    AwsS3Configuration config;

    @GetMapping(value = "/showvids/{id}")
    public ResponseEntity<?> findVideoById(@PathVariable Long id){
        return this.service.show(id)
                .map(video -> ResponseEntity
                                .ok()
                                .body(video))
                .orElse(ResponseEntity
                            .notFound()
                            .build());
    }

    @PostMapping("/upload")
    public ResponseEntity<Video> uploadBasicVideo(@RequestParam String videoName, @RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";

        //Convert file for upload
        File file = service.convertMultiPartFile(multipartFile);

        //Build video obj to store in db with url
        BasicVideo video = new BasicVideo(videoName,multipartFile.getContentType());
        String fileName = service.generateFileName(file.getName());
        String fileUrl = endPointUrl + "/" + fileName;
        video.setVideoPath(fileUrl);

        //Attempt to upload file and if OK store video body in DB
        if(this.awsS3.uploadFile(file,fileName).isSuccessful()){
            BasicVideo videoAddedToDB = this.service.createBasicVideo(video);
                try {
                    return ResponseEntity
                            .created(new URI("/newvideos/" + videoAddedToDB.getVideoId()))
                            .body(videoAddedToDB);
                } catch (URISyntaxException e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
