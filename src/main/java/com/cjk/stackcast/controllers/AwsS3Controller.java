package com.cjk.stackcast.controllers;

import com.cjk.stackcast.aws.Credentials;
import com.cjk.stackcast.aws.DemoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/zc-video-app")
public class AwsS3Controller {

    @Autowired
    Credentials awsService;

    @PostMapping("/addobject")
    public void createObject(@RequestBody DemoObject demoObject) throws Exception{
        this.awsService.uploadFile(demoObject);
    }

}
