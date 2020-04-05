package com.cjk.stackcast.services;

import com.cjk.stackcast.models.User;
import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
import com.cjk.stackcast.repositories.UserRepository;
import com.cjk.stackcast.repositories.VideoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @MockBean
    private VideoRepository videoRepository;


    @Test
    @DisplayName("Test findById Success")
    public void testFindByIdSuccess(){

        // Set up mock object and repository
        BasicVideo mockVideo = new BasicVideo("Test Video","https://testPath.com/test","video/mp4");
        doReturn(mockVideo).when(videoRepository).findVideoByVideoId(1L);

        // Execute call
        Optional<Video> returnVideo = videoService.show(1L);

        Assertions.assertTrue(returnVideo.isPresent(), "No Video was found when there should be");
        Assertions.assertSame(returnVideo.get(),mockVideo, "Models don't match up");
    }


    @Test
    @DisplayName("Test findAll")
    public void testFindAll(){
        // Set up mock object and repository
        BasicVideo mockVideo1 = new BasicVideo("testVideoName1","video/mp4");
        BasicVideo mockVideo2 = new BasicVideo("testVideoName1","video/mp4");
        doReturn(Arrays.asList(mockVideo1,mockVideo2)).when(videoRepository).findAll();

        // Execute call
        List<Video> returnVideoList = (List<Video>) videoService.showAll();

        // Check assertions
        Assertions.assertEquals(2,returnVideoList.size(),"findAll should return 2 videos");
    }

    @Test
    @DisplayName("Test save Video")
    public void testSave(){
        // Set up mock object and repository
        BasicVideo mockVideo1 = new BasicVideo("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(any());

        // Execute call
        BasicVideo returnVideo = videoService.createBasicVideo(mockVideo1);

        Assertions.assertNotNull(returnVideo, "The saved video should not be null");
    }

    @Test
    @DisplayName("Test increment Video views")
    public void testIncrementViews(){
        // Set up mock object and repository
        BasicVideo mockVideo1 = new BasicVideo("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).findVideoByVideoId(1L);
        Integer expected = 1;

        // Execute call
        Integer actual =  videoService.incrementVideoViews(1L).getVideoViews();

        // Assert views incremented by 1
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test update Video name")
    public void updateVideoName(){
        // Set up mock object and repository
        BasicVideo mockVideo1 = new BasicVideo("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).findVideoByVideoId(1L);
        String expected = "updatedName";

        // Execute call
        String actual =  videoService.updateVideoName(1L,expected).getVideoName();

        // Assert name updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test update Video path")
    public void updateVideoPath(){
        // Set up mock object and repository
        BasicVideo mockVideo1 = new BasicVideo("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).findVideoByVideoId(1L);
        String expected = "https://UpdatedtestPath.com/test1";

        // Execute call
        String actual =  videoService.updateVideoPath(1L,expected).getVideoPath();

        // Assert name updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test generate file name")
    public void generateFileNameTest(){
        String original = "test String ";
        String afterGenerate = videoService.generateFileName(original);
        assertNotEquals(original,afterGenerate);
    }
}