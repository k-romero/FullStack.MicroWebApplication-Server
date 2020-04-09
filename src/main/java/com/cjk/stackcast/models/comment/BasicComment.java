package com.cjk.stackcast.models.comment;

import javax.persistence.*;

@Entity
public class BasicComment extends Comment{

    public BasicComment(){
    }
    public BasicComment(Long videoId, String comment) {
        super(videoId,comment);
    }

    public BasicComment(Long videoId, Long userId, String comment) {
        super(videoId, userId, comment);
    }

}
