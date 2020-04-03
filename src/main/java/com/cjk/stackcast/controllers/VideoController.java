package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.UserVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.http.SdkHttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.Date;

@RestController
@RequestMapping(value = "/zc-video-app/videos")
public class VideoController {

    @Autowired
    private VideoService service;
    @Autowired
    private AwsS3Controller awsS3Controller;

    private final String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";

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
        String fileUrl = "";
        BasicVideo video = new BasicVideo();
        video.setVideoName(videoName);
        video.setVideoType(multipartFile.getContentType());
        video.setVideoViews(0);
        try {
            File file = convertMultiPartFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endPointUrl + "/" + fileName;
            video.setVideoPath(fileUrl);
            if(uploadFileFromAwsController(file,fileName).isSuccessful()){
                BasicVideo videoAddedToDB = this.service.createBasicVideo(video);
                try {
                    return ResponseEntity
                            .created(new URI("/newvideos/" + videoAddedToDB.getVideoId()))
                            .body(videoAddedToDB);
                } catch (URISyntaxException e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //AWS Controller Methods
    public SdkHttpResponse uploadFileFromAwsController(File file, String fileName) throws Exception {
       return this.awsS3Controller.uploadFile(file,fileName);
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
    public String generateFileName(MultipartFile multipartFile){
        return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
    }


}
