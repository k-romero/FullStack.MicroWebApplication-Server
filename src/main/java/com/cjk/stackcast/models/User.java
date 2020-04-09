package com.cjk.stackcast.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotEmpty(message = "UserName may not be empty")
    @Size(min=4, max=20)
    private String userName;

    @NotEmpty(message = "Password may not be empty")
    @Size(min=6, max=20)
    private String password;
    private LocalDate dateCreated = LocalDate.now();
    private Boolean isConnected = false;

    public User(String userName) {
        this.userName = userName;
        this.password = "";
        this.dateCreated = LocalDate.now();
    }

    public User(String userName,String password) {
        this.userName = userName;
        this.password = password;
        this.dateCreated = LocalDate.now();
    }


    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
