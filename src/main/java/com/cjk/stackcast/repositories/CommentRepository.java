package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    public List<String> findByVideoId(Long videoId);
}
