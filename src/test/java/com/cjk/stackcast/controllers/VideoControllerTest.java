package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.Video;
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
    @DisplayName("Get /videos/showvids/1 - Found")
    void testGetVideoByIdFound() throws Exception{
        //Setup mocked video
        Video mockVideo = new Video(1L,"Test Video","https://testPath.com/test","video/mp4");
        doReturn(mockVideo).when(videoService).createVideo( mockVideo);
        doReturn(Optional.of(mockVideo)).when(videoService).show(1L);

        //Execute the Get request
        mockMvc.perform(get("/zc-video-app/videos/show/{id}",1))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.videoId",is(1)))
                .andExpect(jsonPath("$.videoName",is("Test Video")))
                .andExpect(jsonPath("$.videoPath",is("https://testPath.com/test")))
                .andExpect(jsonPath("$.videoType",is("video/mp4")))
                .andExpect(jsonPath("$.videoViews",is(0)));
    }

    @Test
    @DisplayName("GET /videos/showvids/1 - Not Found")
    void testGetVideoByIdNotFound() throws Exception {
        //Setup our mocked service
        doReturn(Optional.empty()).when(videoService).show(1L);

        //Execute the GET request
        mockMvc.perform(get("/zc-video-app/videos/show/{id}",1))

                //Validate that we get a 404 Not Found
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /videos/updateName/{id}")
    void testUpdateName() throws Exception{
        //Given
        String newName = "newVideoName";
        Video putVideo = new Video(1L,newName,"https://testPath.com/test","video/mp4");
        doReturn(putVideo).when(videoService).updateVideoName(1L,newName);

        //Execute
        mockMvc.perform(put("/zc-video-app/videos/updateName/{id}",1)
                            .param("videoName",newName))

                            //Validate
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.videoName",is(newName)));
    }

    @Test
    @DisplayName("PUT /videos/incrementViews/{id}")
    void testIncrementViews() throws Exception{

        //TODO write actual test
        //Given
        Integer newViewCount = 0;
        Video putVideo = new Video(1L,"Test Videos","https://testPath.com/test","video/mp4");
        doReturn(putVideo).when(videoService).incrementVideoViews(1L);

        //Execute
        mockMvc.perform(put("/zc-video-app/videos/incrementViews/{id}",1))

                //Validate
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.videoViews",is(newViewCount)));
    }
}