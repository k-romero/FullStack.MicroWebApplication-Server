package com.cjk.stackcast.services;


import com.cjk.stackcast.aws.AwsS3Configuration;
import com.cjk.stackcast.aws.DemoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
public class AwsS3Service {

    @Autowired
    AwsS3Configuration config;

    public PutObjectResponse uploadFile(File file,String fileName) throws S3Exception,
            AwsServiceException, SdkClientException, URISyntaxException,
            FileNotFoundException {

        //Create Object Request
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(config.getBucket()).key(fileName)
                .acl(ObjectCannedACL.PUBLIC_READ_WRITE)
                .build();

        //Generate S3Client And Post Object To S3 Bucket
        return config.generateS3Client().putObject(putObjectRequest, RequestBody.fromFile(file));
    }

}
