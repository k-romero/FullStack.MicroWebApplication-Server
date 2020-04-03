package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/zc-video-app/videos")
public class VideoController {

    @Autowired
    private VideoService service;

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
        Video tempVideo = service.saveBasicVideo(videoName,multipartFile);
        if(tempVideo != null){
            return new ResponseEntity<>(tempVideo,HttpStatus.OK);
        } else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }


}
