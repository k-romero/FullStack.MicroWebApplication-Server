package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.Comment;
import com.cjk.stackcast.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByVideo_VideoId(Long videoId);
}
