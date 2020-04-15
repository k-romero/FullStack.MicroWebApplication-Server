package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping( value = "zc-video-app/comments")
@CrossOrigin
public class CommentController {

    private CommentService service;

    @Autowired
    public CommentController(CommentService service){
        this.service = service;
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<?> showComment(@PathVariable Long id){
        return this.service.showComment(id)
                .map(comment -> ResponseEntity.ok().body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/show")
    public ResponseEntity<Iterable<Comment>> showAll(){
        return new ResponseEntity<>(service.showAll() , HttpStatus.OK);
    }

    @GetMapping("/showByVideo/{videoId}")
    public ResponseEntity<List<Comment>> findCommentsByVideoId(@PathVariable Long videoId){
        return new ResponseEntity<>(service.findAllCommentsByVideoId(videoId),HttpStatus.OK);
    }

    @PostMapping("/create/{videoId}")
    public ResponseEntity<Comment> create(@PathVariable Long videoId, @RequestBody Comment comment) throws Exception {
        Comment newComment = this.service.create(videoId,comment);
        try {
            return ResponseEntity
                    .created( new URI("/create" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping(value ="/delete/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteComment(id) , HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestParam String newMessage) throws Exception {
        return new ResponseEntity<>(service.updateCommentMessage(id,newMessage),HttpStatus.OK);
    }

}
