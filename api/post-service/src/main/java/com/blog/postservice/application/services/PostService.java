package com.blog.postservice.application.services;

import com.blog.postservice.application.gateways.PostRepositoryGateway;
import com.blog.postservice.core.cases.CreatePostUseCase;
import com.blog.postservice.core.exceptions.CustomException;
import com.blog.postservice.domain.entities.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class PostService implements CreatePostUseCase {

    private final PostRepositoryGateway postGateway;

    @Autowired
    public PostService(PostRepositoryGateway postGateway) {
        this.postGateway = postGateway;
    }

    @Override
    public Post createPost(String title, String description) {
        log.info("Sending the content to be saved in the base.");
        var postCreated = postGateway.createPost(title, description);
        if (postCreated == null) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error while the post was saved."
            );
        }
        log.info("Sending the content to be mapping.");
        return postCreated;
    }
}
