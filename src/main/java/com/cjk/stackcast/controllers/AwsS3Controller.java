package com.cjk.stackcast.controllers;

import com.cjk.stackcast.aws.DemoObject;
import com.cjk.stackcast.services.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.http.SdkHttpResponse;

@RestController
@RequestMapping(value = "/zc-video-app")
public class AwsS3Controller {

    @Autowired
    AwsS3Service awsS3Service;

    @PostMapping("/addobject")
    public SdkHttpResponse createObject(@RequestBody DemoObject demoObject) throws Exception{
       return this.awsS3Service.uploadFile(demoObject).sdkHttpResponse();
    }

}
