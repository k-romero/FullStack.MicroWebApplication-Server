package com.cjk.stackcast.services;

import com.cjk.stackcast.models.DAOUser;
import com.cjk.stackcast.repositories.UserDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserDaoRepository repo;

    @Autowired
    public UserService(UserDaoRepository userDaoRepository){
        this.repo = userDaoRepository;
    }

    public Optional<DAOUser> showUser(Long id){
        return repo.findById(id);
    }

    public Iterable<DAOUser> showAll(){
        return repo.findAll();
    }

    public Optional<DAOUser> findByUserName(String userName){
        return repo.findUserByUserName(userName);
    }

    public DAOUser create(DAOUser DAOUser){
        if(!findByUserName(DAOUser.getUserName()).isPresent()){
            return repo.save(DAOUser);
        }
         else {
             throw new IllegalArgumentException("That username is already taken");
        }
    }

    public DAOUser updateUserName(Long userId, String userName){
        DAOUser originalDAOUser = repo.getOne(userId);
        originalDAOUser.setUserName(userName);
        return repo.save(originalDAOUser);
    }

    public DAOUser updatePassword(Long userId, String password){
        DAOUser originalDAOUser = repo.getOne(userId);
        originalDAOUser.setPassword(password);
        return repo.save(originalDAOUser);
    }

    public DAOUser updateConnection(Long userId){
        DAOUser originalDAOUser = repo.getOne(userId);
        if(originalDAOUser.getIsConnected()){
            originalDAOUser.setIsConnected(false);
        } else
            originalDAOUser.setIsConnected(true);
        return repo.save(originalDAOUser);
    }

    public Boolean deleteUser(Long userId){
        repo.deleteById(userId);
        return true;
    }

}
