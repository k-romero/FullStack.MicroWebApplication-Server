package com.cjk.stackcast.services;

import com.cjk.stackcast.controllers.AwsS3Controller;
import com.cjk.stackcast.models.comment.BasicComment;
import com.cjk.stackcast.models.comment.Comment;
import com.cjk.stackcast.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository comentRopo;
    @Autowired
    private AwsS3Controller awsS3Controller;

}
