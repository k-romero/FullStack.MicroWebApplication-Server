package com.cjk.stackcast.services;


import com.cjk.stackcast.aws.AwsConfiguration;
import com.cjk.stackcast.aws.DemoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@Service
public class AwsService {

    @Autowired
    AwsConfiguration config;

    public void uploadFile(DemoObject demoObject) throws S3Exception,
            AwsServiceException, SdkClientException, URISyntaxException,
            FileNotFoundException {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(config.getBucket()).key(demoObject.getName())
                .acl(ObjectCannedACL.PUBLIC_READ_WRITE)
                .build();

        File file = new File(getClass().getClassLoader()
                .getResource(demoObject.getName()).getFile());

        config.generateS3Client().putObject(putObjectRequest, RequestBody.fromFile(file));
    }

}
