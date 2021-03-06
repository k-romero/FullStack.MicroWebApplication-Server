package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findAllByUserId(Long userId);

}
