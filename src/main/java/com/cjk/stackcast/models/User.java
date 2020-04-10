package com.cjk.stackcast.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotEmpty(message = "UserName may not be empty")
    @Size(min=4, max=20)
    @Column(name = "USER_NAME")
    private String userName;

    @NotEmpty(message = "Password may not be empty")
    @Size(min=6, max=20)
    @Column(name = "PASSWORD")
    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "IS_CONNECTED")
    private Boolean isConnected = false;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
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

    public User(Long id, String userName,String password) {
        this.id = id;
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
