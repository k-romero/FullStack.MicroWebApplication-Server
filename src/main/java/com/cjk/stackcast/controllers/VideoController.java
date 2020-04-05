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

    @GetMapping("/showvids/{id}")
    public ResponseEntity<?> findVideoById(@PathVariable Long id){
        return this.service.show(id)
                .map(video -> ResponseEntity
                        .ok()
                        .body(video))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping("/showvids")
    public ResponseEntity<Iterable<Video>> showVideos() {
        return new ResponseEntity<>(service.showAll(),HttpStatus.OK);

}
    @PostMapping("/upload")
    public ResponseEntity<Video> uploadBasicVideo(@RequestParam String videoName, @RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        Video tempVideo = service.saveBasicVideo(videoName,multipartFile);
        if(tempVideo != null){
            return new ResponseEntity<>(tempVideo,HttpStatus.OK);
        } else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
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
