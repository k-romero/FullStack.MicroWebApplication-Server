package com.cjk.stackcast.services;

import com.cjk.stackcast.models.User;
import com.cjk.stackcast.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository repo;

    @Autowired
    public UserService(UserRepository userRepository){
        this.repo = userRepository;
    }

    public Optional<User> showUser(Long id){
        return repo.findById(id);
    }

    public Iterable<User> showAll(){
        return repo.findAll();
    }

    public Optional<User> findByUserName(String userName){
        return repo.findUserByUserName(userName);
    }

    public User create(User user){
        if(!findByUserName(user.getUserName()).isPresent()){
            return repo.save(user);
        }
         else {
             throw new IllegalArgumentException("That username is already taken");
        }
    }

    public User updateUserName(Long userId, String userName){
        User originalUser = repo.getOne(userId);
        originalUser.setUserName(userName);
        return repo.save(originalUser);
    }

    public User updatePassword(Long userId, String password){
        User originalUser = repo.getOne(userId);
        originalUser.setPassword(password);
        return repo.save(originalUser);
    }

    public User updateConnection(Long userId){
        User originalUser = repo.getOne(userId);
        if(originalUser.getIsConnected()){
            originalUser.setIsConnected(false);
        } else
            originalUser.setIsConnected(true);
        return repo.save(originalUser);
    }

    public Boolean deleteUser(Long userId){
        repo.deleteById(userId);
        return true;
    }

}
