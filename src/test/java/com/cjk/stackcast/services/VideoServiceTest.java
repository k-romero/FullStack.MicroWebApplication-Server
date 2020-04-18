package com.cjk.stackcast.services;

import com.cjk.stackcast.models.Video;
import com.cjk.stackcast.repositories.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


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
        Video mockVideo = new Video("Test Video","https://testPath.com/test","video/mp4");
        doReturn(Optional.of(mockVideo)).when(videoRepository).findById(1L);

        // Execute call
        Optional<Video> returnVideo = videoService.show(1L);

        Assertions.assertTrue(returnVideo.isPresent(), "No Video was found when there should be");
        Assertions.assertSame(returnVideo.get(),mockVideo, "Models don't match up");
    }


    @Test
    @DisplayName("Test findAll")
    public void testFindAll(){
        // Set up mock object and repository
        Video mockVideo1 = new Video("testVideoName1","video/mp4");
        Video mockVideo2 = new Video("testVideoName1","video/mp4");
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
        Video mockVideo1 = new Video("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(any());

        // Execute call
        Video returnVideo = videoService.saveVideo(mockVideo1);

        Assertions.assertNotNull(returnVideo, "The saved video should not be null");
    }

    @Test
    @DisplayName("Test increment Video views")
    public void testIncrementViews(){
        // Set up mock object and repository
        Video mockVideo1 = new Video("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).getOne(1L);
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
        Video mockVideo1 = new Video("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).getOne(1L);
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
        Video mockVideo1 = new Video("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).getOne(1L);
        String expected = "https://UpdatedtestPath.com/test1";

        // Execute call
        String actual =  videoService.updateVideoPath(1L,expected).getVideoPath();

        // Assert name updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test getAllUserVideos - Success")
    public void getUserVideosTest(){
        // Set up mock object and repository
        Video mockVideo1 = new Video(1L,"testVideoName1","https://testPath.com/test1" ,"video/mp4");
        Video mockVideo2 = new Video(2L,"testVideoName1","https://testPath.com/test1" ,"video/mp4");
        mockVideo1.setUserId(1L);
        mockVideo2.setUserId(1L);
        Iterable<Video> userVideos = new ArrayList<>(Arrays.asList(mockVideo1,mockVideo2));
        doReturn(userVideos).when(videoRepository).findAllByUserId(1L);

        Integer expected = 2;

        // Execute call
        ArrayList<Video> actual = (ArrayList<Video>)videoService.showAllUserVids(1L);

        // Assert name updated
        Assertions.assertEquals(expected,actual.size());
    }

    @Test
    @DisplayName("Test setUser - Success")
    public void setUserTest(){
        // Set up mock object and repository
        Video mockVideo1 = new Video("testVideoName1","https://testPath.com/test1" ,"video/mp4");
        doReturn(mockVideo1).when(videoRepository).save(mockVideo1);
        doReturn(mockVideo1).when(videoRepository).getOne(1L);
        Long expected = 1L;

        // Execute call
        Long actual =  videoService.setUser(1L,expected).getUserId();

        // Assert name updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test generate file name")
    public void generateFileNameTest(){
        String original = "test String ";
        Video video = new Video();
        video.setVideoName(original);
        String afterGenerate = videoService.generateFileName(video.getVideoName());
        assertNotEquals(original,afterGenerate);
    }


    @Test
    @DisplayName("Test verify file type - Exception Thrown")
    public void verifyFileTypeFailTest(){
        MockMultipartFile mockMultipartFile = new MockMultipartFile("mockImageFile","mockImage.png",
                "image/png", "testImageData".getBytes());
        Assertions.assertThrows(InputMismatchException.class,
                () -> videoService.verifyFileType("testVideoName",mockMultipartFile));
    }

    @Test
    @DisplayName("Test convert MultiPartFile - Produces File")
    public void convertFileTest() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("mockVideoFile","mockVideo.mp4",
                "video/mp4", "testVideoData".getBytes());
        String expected = "mockVideo.mp4";
        File convertedFile = videoService.convertMultiPartFile(mockMultipartFile);

        String actual = convertedFile.getName();

        assertEquals(expected,actual);
    }
}