package com.cjk.stackcast.controllers;

import com.cjk.stackcast.aws.DemoObject;
import com.cjk.stackcast.services.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.http.SdkHttpResponse;

import java.io.File;

@RestController
@RequestMapping(value = "/zc-video-app")
public class AwsS3Controller {

    @Autowired
    AwsS3Service awsS3Service;

    @PostMapping("/addvideo")
    public SdkHttpResponse uploadFile(@RequestPart(value = "file") File file, String fileName) throws Exception{
       return this.awsS3Service.uploadFile(file,fileName).sdkHttpResponse();
    }

}
