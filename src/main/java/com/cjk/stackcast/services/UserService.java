package com.cjk.stackcast.services;

import com.cjk.stackcast.models.User;
import com.cjk.stackcast.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public Optional<User> showUser(Long id){
        return repo.findById(id);
    }

}
