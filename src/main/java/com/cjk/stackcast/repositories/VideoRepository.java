package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Iterable<Video> findByUser_Id(Long userId);
}
