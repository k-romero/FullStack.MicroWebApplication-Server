package com.cjk.stackcast.models.video;

import javax.persistence.Entity;

@Entity
public class UserVideo extends Video{
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
