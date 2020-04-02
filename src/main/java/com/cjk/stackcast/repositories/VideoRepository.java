package com.cjk.stackcast.repositories;

import com.cjk.stackcast.models.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

    public Video findVideoByVideoId(Long videoId);

}
