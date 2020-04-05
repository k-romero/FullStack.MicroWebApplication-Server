package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.comment.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

    public Comment findCommentsByVideoId(Long videoId);

}
