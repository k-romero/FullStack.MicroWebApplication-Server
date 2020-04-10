package com.cjk.stackcast.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Service
public class AwsS3Configuration {

    @Value("${cloud.aws.credentials.accessKey}")
    private String key;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.credentials.bucket}")
    private String bucket;

    private final Region region = Region.US_EAST_1;

    private final String endPointUrl = "https://zip-code-video-app.s3.amazonaws.com";

    @Bean
    public String getBucket(){
        return this.bucket;
    }

    @Bean
    public S3Client generateS3Client(){
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);
        return S3Client.builder().credentialsProvider(StaticCredentialsProvider
                                    .create(awsCreds))
                                    .region(region)
                                    .build();
    }

    public String getEndPointUrl(){
        return endPointUrl;
    }

}
