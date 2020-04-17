package com.cjk.stackcast.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Users")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "IS_CONNECTED")
    private Boolean isConnected = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private List<Video> userVideos;


    public DAOUser(String userName) {
        this.userName = userName;
        this.password = "";
        this.dateCreated = LocalDate.now();
        this.userVideos = new ArrayList<>();
    }

    public DAOUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.userVideos = new ArrayList<>();
    }

    public DAOUser(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.userVideos = new ArrayList<>();
    }

    public DAOUser(Long id, String userName, String password, LocalDate date, Boolean isConnected) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.dateCreated = date;
        this.isConnected = isConnected;
    }

    public DAOUser() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DAOUser DAOUser = (DAOUser) o;
        return Objects.equals(id, DAOUser.id) &&
                Objects.equals(userName, DAOUser.userName) &&
                Objects.equals(password, DAOUser.password) &&
                Objects.equals(dateCreated, DAOUser.dateCreated) &&
                Objects.equals(isConnected, DAOUser.isConnected) &&
                Objects.equals(userVideos, DAOUser.userVideos);
    }

}
