package com.cjk.stackcast.aws;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AwsS3ConfigurationTest {

    @Autowired
    public AwsS3Configuration awsS3Configuration;


    @Test
    void getBucket() {
        assertFalse(awsS3Configuration.getBucket().isEmpty());
    }

    @Test
    void generateS3Client() {
        assertNotNull(awsS3Configuration.generateS3Client());
    }

    @Test
    void getEndPointUrl() {
        String expected = "https://zip-code-video-app.s3.amazonaws.com";
        String actual = awsS3Configuration.getEndPointUrl();
        assertEquals(expected,actual);
    }
}