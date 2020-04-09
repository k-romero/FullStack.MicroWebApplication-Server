package com.cjk.stackcast.models.comment;

import javax.persistence.*;

@Entity
public class UserComment extends Comment {

    public UserComment(){
    }
    public UserComment(Long videoId, Long userId, String comment) {
        super(videoId, userId, comment);
    }
}
