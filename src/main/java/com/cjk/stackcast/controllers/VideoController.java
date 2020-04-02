package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @PostMapping("/createbasic")
    public ResponseEntity<Video> createBasicVideo(@RequestBody BasicVideo basicVideo) {
        Video video = this.service.createBasicVideo(basicVideo);

        try {
            return ResponseEntity
                    .created(new URI("/createbasic/" + video.getVideoId()))
                    .body(video);
        } catch (URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
