package com.cjk.stackcast.models;

import com.cjk.stackcast.models.comment.Comment;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class User {
// Instance Fields
    private Long userId;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dateCreated;
    private ArrayList<Comment> commentHistory;
    private ArrayList<Long> uploadHistory;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = "";
        this.dateCreated = LocalDate.now();
        this.commentHistory = new ArrayList<>();
        this.uploadHistory = new ArrayList<>();
    }
    public User(Long userId,String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = "";
        this.dateCreated = LocalDate.now();
        this.commentHistory = new ArrayList<>();
        this.uploadHistory = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Comment> getCommentHistory() {
        return commentHistory;
    }

    public ArrayList<Long> getUploadHistory() {
        return uploadHistory;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setCommentHistory(ArrayList<Comment> commentHistory) {
        this.commentHistory = commentHistory;
    }

    public void setUploadHistory(ArrayList<Long> uploadHistory) {
        this.uploadHistory = uploadHistory;
    }


}
