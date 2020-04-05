package com.cjk.stackcast.services;

import com.cjk.stackcast.aws.AwsS3Configuration;
import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.models.video.Video;
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
    private VideoRepository repo;
    @Autowired
    private AwsS3Configuration config;

    public Optional<Video> show(Long videoId){
        if(repo.findById(videoId).isPresent()){
            return Optional.of(repo.findVideoByVideoId(videoId));
        } else throw new NullPointerException();
    }

    public Iterable<Video> showAll(){
        return repo.findAll();
    }

    public BasicVideo createBasicVideo(BasicVideo basicVideo){
        return repo.save(basicVideo);
    }

    public BasicVideo saveBasicVideo(String videoName, MultipartFile multipartFile) throws Exception{
        String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";
        File file = convertMultiPartFile(multipartFile);
        BasicVideo video = new BasicVideo(videoName,multipartFile.getContentType());
        String fileName = generateFileName(file.getName());
        String fileUrl = endPointUrl + "/" + fileName;
        video.setVideoPath(fileUrl);
        if(uploadFile(file,fileName).isSuccessful()){
           return createBasicVideo(video);
        } else
            return null;
    }

    public boolean delete(Long videoId) throws Exception {
        //TODO resolve delete from s3 bucket per "key"(filename)
//        Video vid = repo.findVideoByVideoId(videoId);
//        awsS3.deleteFile(vid.getVideoName());
        return repo.deleteVideoByVideoId(videoId);
    }

    public BasicVideo incrementVideoViews(Long videoId){
        BasicVideo video = (BasicVideo) repo.findVideoByVideoId(videoId);
        video.setVideoViews(video.getVideoViews() + 1);
        return repo.save(video);
    }

    public BasicVideo updateVideoName(Long videoId, String newName){
        BasicVideo video = (BasicVideo) repo.findVideoByVideoId(videoId);
        video.setVideoName(newName);
        return repo.save(video);
    }

    public BasicVideo updateVideoPath(Long videoId, String newPath){
        BasicVideo video = (BasicVideo) repo.findVideoByVideoId(videoId);
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

    public SdkHttpResponse uploadFile(File file, String fileName) throws S3Exception,
            AwsServiceException, SdkClientException, URISyntaxException,FileNotFoundException {
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
