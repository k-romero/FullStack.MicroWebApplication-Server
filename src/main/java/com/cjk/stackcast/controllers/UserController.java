package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.DAOUser;
import com.cjk.stackcast.models.TestUser;
import com.cjk.stackcast.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/zc-video-app/users")
@CrossOrigin
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

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

    @GetMapping("/find")
    public ResponseEntity<?> show(@RequestParam String userName){
        return this.service.findByUserName(userName)
                .map(user -> ResponseEntity
                        .ok()
                        .body(user))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public TestUser validateLogin() {
        return new TestUser("User successfully authenticated");
    }

    @RequestMapping("/show")
    public ResponseEntity<Iterable<DAOUser>> showAll(){
        return new ResponseEntity<>(service.showAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DAOUser> create(@RequestBody DAOUser DAOUser, BindingResult bindingResult){
        //TODO create validation forms to check user credentials are long enough
        DAOUser newDAOUser = this.service.create(DAOUser);
        try {
            return ResponseEntity
                    .created(new URI("/create/" + newDAOUser.getId()))
                    .body(newDAOUser);
        }catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateName/{userId}")
    public ResponseEntity<DAOUser> updateName(@PathVariable Long userId, @RequestParam String userName){
       return new ResponseEntity<>(service.updateUserName(userId,userName),HttpStatus.OK);
    }

    @PutMapping("/updatePw/{userId}")
    public ResponseEntity<DAOUser> updatePassWord(@PathVariable Long userId, @RequestParam String passWord){
        return new ResponseEntity<>(service.updatePassword(userId,passWord),HttpStatus.OK);
    }

    @PutMapping("/login/{userId}")
    public ResponseEntity<DAOUser> userLogin(@PathVariable Long userId){
        return new ResponseEntity<>(service.updateConnection(userId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> userDelete(@PathVariable Long userId){
        return new ResponseEntity<>(service.deleteUser(userId),HttpStatus.OK);
    }


}
