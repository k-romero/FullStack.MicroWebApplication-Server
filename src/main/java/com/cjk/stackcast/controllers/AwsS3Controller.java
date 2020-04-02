package com.cjk.stackcast.controllers;

import com.cjk.stackcast.aws.DemoObject;
import com.cjk.stackcast.services.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@RestController
@RequestMapping(value = "/zc-video-app")
public class AwsS3Controller {

    @Autowired
    AwsService awsService;

    @PostMapping("/addobject")
    public SdkHttpResponse createObject(@RequestBody DemoObject demoObject) throws Exception{
       return this.awsService.uploadFile(demoObject).sdkHttpResponse();
    }

}
