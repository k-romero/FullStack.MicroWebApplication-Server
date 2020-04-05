package com.cjk.stackcast.models.comment;

import javax.persistence.*;

@Entity
public class BasicComment extends Comment{

    public BasicComment(){
    }
    public BasicComment(Long commentId, Long videoId, Long userId, String comment) {
        super(commentId, videoId, userId, comment);
    }

    public BasicComment(Long videoId, Long userId, String comment) {
        super(videoId, userId, comment);
    }

}
