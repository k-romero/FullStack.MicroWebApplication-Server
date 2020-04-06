package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.User;
import com.cjk.stackcast.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/zc-video-app/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return this.service.showUser(id)
                .map(user -> ResponseEntity
                        .ok()
                        .body(user))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @RequestMapping("/show")
    public ResponseEntity<Iterable<User>> showAll(){
        return new ResponseEntity<>(service.showAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){

        //TODO create validation forms to check user credentials are long enough

        User newUser = this.service.create(user);
        try {
            return ResponseEntity
                    .created(new URI("/create/" + newUser.getUserId()))
                    .body(newUser);
        }catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateName/{userId}")
    public ResponseEntity<User> updateName(@PathVariable Long userId, @RequestParam String userName){
       return new ResponseEntity<>(service.updateUserName(userId,userName),HttpStatus.OK);
    }

    @PutMapping("/updatePw/{userId}")
    public ResponseEntity<User> updatePassWord(@PathVariable Long userId, @RequestParam String passWord){
        return new ResponseEntity<>(service.updatePassword(userId,passWord),HttpStatus.OK);
    }

    @PutMapping("/login/{userId}")
    public ResponseEntity<User> userLogin(@PathVariable Long userId){
        return new ResponseEntity<>(service.updateConnection(userId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> userDelete(@PathVariable Long userId){
        return new ResponseEntity<>(service.deleteUser(userId),HttpStatus.OK);
    }


}
