package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.services.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {

    @MockBean
    private VideoService videoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /videos/1 - Found")
    void testGetVideoByIdFound() throws Exception{
        //Setup mocked video

        //TODO figure out single repo with multiple types of videos
        Video mockVideo = new BasicVideo("Test Video","https://testPath.com/test","video/mp4");
        doReturn(mockVideo).when(videoService).createBasicVideo((BasicVideo) mockVideo);
        doReturn(mockVideo).when(videoService).show(1L);

        //Execute the Get request
        mockMvc.perform(get("zc-video-app/videos/showvids/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.videoId",is(1)))
                .andExpect(jsonPath("$.videoName",is("Test Video")))
                .andExpect(jsonPath("$.videoPath",is("https://testPath.com/test")))
                .andExpect(jsonPath("$.videoType",is("video/mp4")))
                .andExpect(jsonPath("$.videoViews",is(0)));
    }


}