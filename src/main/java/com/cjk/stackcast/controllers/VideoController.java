package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.Video;
import com.cjk.stackcast.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping(value = "/zc-video-app/videos")
@CrossOrigin
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
    public ResponseEntity<Iterable<Video>> showUserVideos(@PathVariable Long userId) {
        return new ResponseEntity<>(service.showAllUserVids(userId),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Video> create(@RequestBody Video video){
        Video newVideo = this.service.saveVideo(video);
        try {
            return ResponseEntity
                    .created(new URI("/create/" + newVideo.getVideoId()))
                    .body(newVideo);
        }catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<Video> uploadVideoToCloud(@PathVariable Long userId, @RequestParam String videoName, @RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        Video tempVideo = service.uploadVideo(videoName,userId,multipartFile);
        if(tempVideo != null){
            System.out.println(tempVideo.toString());
            return new ResponseEntity<>(tempVideo,HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/setUser/{videoId}")
    public ResponseEntity<Video> setUser(@PathVariable Long videoId, @RequestParam Long userId) {
        return new ResponseEntity<>(service.setUser(videoId,userId), HttpStatus.OK);
    }

    @PutMapping("/updateName/{id}")
    @ResponseBody
    public ResponseEntity<Video> updateVideoName(@RequestParam String videoName, @PathVariable Long id) {
        return new ResponseEntity<>(service.updateVideoName(id,videoName), HttpStatus.OK);
    }

    @GetMapping("/incrementViews/{id}")
    public ResponseEntity<Video> incrementViews(@PathVariable Long id) {
        return new ResponseEntity<>(service.incrementVideoViews(id), HttpStatus.OK);
    }

    @GetMapping("/incrementLikes/{id}")
    public ResponseEntity<Video> incrementLikes(@PathVariable Long id) {
        return new ResponseEntity<>(service.incrementLikes(id), HttpStatus.OK);
    }

    @GetMapping("/incrementDisLikes/{id}")
    public ResponseEntity<Video> incrementDisLikes(@PathVariable Long id) {
        return new ResponseEntity<>(service.incrementDisLikes(id), HttpStatus.OK);
    }

    @PutMapping("/updatePath/{id}")
    public ResponseEntity<Video> updateVideoPath(@PathVariable Long id, @RequestParam String videoPath) {
        return new ResponseEntity<>(service.updateVideoPath(id,videoPath), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVideo(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
