package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    public Comment findCommentsByVideoId(Long videoId);

}
