package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.Video;
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

    @GetMapping("/show/{id}")
    public ResponseEntity<?> findVideoById(@PathVariable Long id){
        return this.service.show(id)
                .map(video -> ResponseEntity
                        .ok()
                        .body(video))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping("/show")
    public ResponseEntity<Iterable<Video>> showVideos() {
        return new ResponseEntity<>(service.showAll(),HttpStatus.OK);
    }

    @GetMapping("/showUserVideos/{userId}")
    public ResponseEntity<Iterable<Video>> showVideos(@PathVariable Long userId) {
        return new ResponseEntity<>(service.showAllUserVids(userId),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Video> createBasicVideo(@RequestBody Video video) throws Exception {
        return new ResponseEntity<>(service.createVideo(video),HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<Video> uploadBasicVideo(@RequestParam String videoName, @RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        Video tempVideo = service.saveVideo(videoName,multipartFile);
        if(tempVideo != null){
            return new ResponseEntity<>(tempVideo,HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/setUser/{videoId}")
    public ResponseEntity<Video> setUser(@PathVariable Long videoId, @RequestParam Long userId) {
        return new ResponseEntity<>(service.setUser(videoId,userId), HttpStatus.OK);
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<Video> updateVideoName(@RequestParam String videoName, @PathVariable Long id) {
        return new ResponseEntity<>(service.updateVideoName(id,videoName), HttpStatus.OK);
    }

    @PutMapping("/incrementViews/{id}")
    public ResponseEntity<Video> incrementViews(@PathVariable Long id) {
        return new ResponseEntity<>(service.incrementVideoViews(id), HttpStatus.OK);
    }

    @PutMapping("/updatePath/{id}")
    public ResponseEntity<Video> updateVideoPath(@PathVariable Long id, @RequestParam String videoPath) {
        return new ResponseEntity<>(service.updateVideoPath(id,videoPath), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVideo(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(service.delete(id),HttpStatus.GONE);
    }
}
