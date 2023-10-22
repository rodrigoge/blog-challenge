package com.blog.postservice.adapters.controllers;

import com.blog.postservice.adapters.mappers.PostMapper;
import com.blog.postservice.application.services.PostService;
import com.blog.postservice.infrastructure.requests.CreatePostRequest;
import com.blog.postservice.infrastructure.responses.CreatePostResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Log4j2
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        log.info("Entering the create post flow.");
        var createdPost = postService.createPost(request.title(), request.description());
        var response = postMapper.fromPostEntity(createdPost);
        log.info("Finishing the create post flow.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
