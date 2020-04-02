package com.cjk.stackcast.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;

@Configuration
@Service
public class Credentials {

    @Value("${cloud.aws.credentials.accessKey}")
    private String key;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.credentials.bucket}")
    private String bucket;

    private final Region region = Region.US_EAST_2;

    private S3Client s3Client;

    @PostConstruct
    public void initialize(){
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);

        s3Client = S3Client.builder()
                                    .credentialsProvider(StaticCredentialsProvider
                                    .create(awsCreds))
                                    .region(region)
                                    .build();
    }

    public void uploadFile(DemoObject demoObject) throws S3Exception,
            AwsServiceException, SdkClientException, URISyntaxException,
            FileNotFoundException {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                            .bucket(bucket).key(demoObject.getName())
                                            .acl(ObjectCannedACL.PUBLIC_READ_WRITE)
                                            .build();

        File file = new File(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(demoObject.getName())).getFile());

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    }


}
