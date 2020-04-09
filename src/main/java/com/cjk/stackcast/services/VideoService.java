package com.cjk.stackcast.services;

import com.cjk.stackcast.aws.AwsS3Configuration;
import com.cjk.stackcast.models.User;
import com.cjk.stackcast.models.Video;
import com.cjk.stackcast.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.s3.model.*;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private AwsS3Configuration config;

    @Autowired
    private VideoRepository repo;

    @Autowired
    private UserService userService;

    public Optional<Video> show(Long videoId){
            return repo.findById(videoId);
    }

    public Iterable<Video> showAll(){
        return repo.findAll();
    }

    public Iterable<Video> showAllUserVids(Long userId){
        return repo.findByUser_Id(userId);
    }

    public Video createVideo(Video basicVideo){
        return repo.save(basicVideo);
    }

    public Video saveVideo(String videoName, MultipartFile multipartFile) throws Exception{
        String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";
        File file = convertMultiPartFile(multipartFile);
        Video video = new Video(videoName,multipartFile.getContentType());
        String fileName = generateFileName(file.getName());
        String fileUrl = endPointUrl + "/" + fileName;
        video.setVideoPath(fileUrl);

        if(uploadFile(file,fileName).isSuccessful()){
           return createVideo(video);
        } else
            return null;
    }

    public boolean delete(Long videoId) throws Exception {
        //TODO resolve delete from s3 bucket per "key"(filename)
        repo.deleteById(videoId);
        return true;
    }

    public Video setUser(Long videoId, Long userId){
        Video video = repo.getOne(videoId);
        User user = userService.getUser(userId);
        video.setUser(user);
        return repo.save(video);
    }

    public Video incrementVideoViews(Long videoId){
        Video video = repo.getOne(videoId);
        video.setVideoViews(video.getVideoViews() + 1);
        return repo.save(video);
    }

    public Video updateVideoName(Long videoId, String newName){
        Video video = repo.getOne(videoId);
        video.setVideoName(newName);
        return repo.save(video);
    }

    public Video updateVideoPath(Long videoId, String newPath){
        Video video = repo.getOne(videoId);
        video.setVideoPath(newPath);
        return repo.save(video);
    }

    public File convertMultiPartFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    public String generateFileName(String fileName){
        return new Date().getTime() + "-" + fileName.replace(" ", "_");
    }

    public SdkHttpResponse uploadFile(File file, String fileName) throws S3Exception, AwsServiceException, SdkClientException, URISyntaxException,FileNotFoundException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(config.getBucket()).key(fileName)
                .acl(ObjectCannedACL.PUBLIC_READ_WRITE)
                .build();
        return config.generateS3Client().putObject(putObjectRequest, RequestBody.fromFile(file)).sdkHttpResponse();
    }

    public DeleteObjectResponse deleteFile(String fileName){
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(config.getBucket()).key(fileName).build();
        return config.generateS3Client().deleteObject(deleteObjectRequest);
    }









}
