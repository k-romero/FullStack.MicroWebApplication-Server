package com.cjk.stackcast.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "UserName may not be empty")
    @Size(min=4, max=20)
    private String userName;

    @NotEmpty(message = "Password may not be empty")
    @Size(min=6, max=20)
    private String password;
    private LocalDate dateCreated = LocalDate.now();
    private Boolean isConnected = false;

    @OneToMany
    private List<Video> userVideos;

    public User(String userName) {
        this.userName = userName;
        this.password = "";
        this.dateCreated = LocalDate.now();
        this.userVideos = new ArrayList<>();
    }

    public User(String userName,String password) {
        this.userName = userName;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.userVideos = new ArrayList<>();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Video> getUserVideos() {
        return userVideos;
    }

    public void setUserVideos(List<Video> userVideos) {
        this.userVideos = userVideos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(Boolean connected) {
        isConnected = connected;
    }


}
