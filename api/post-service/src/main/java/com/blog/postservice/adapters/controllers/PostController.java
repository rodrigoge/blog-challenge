package com.blog.postservice.adapters.controllers;

import com.blog.postservice.application.services.PostService;
import com.blog.postservice.infrastructure.requests.CreatePostRequest;
import com.blog.postservice.infrastructure.requests.GetPostsRequest;
import com.blog.postservice.infrastructure.requests.OrderEnumRequest;
import com.blog.postservice.infrastructure.responses.PostResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@Log4j2
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        log.info("Entering the create post flow.");
        var response = postService.createPost(request.title(), request.description());
        log.info("Finishing the create post flow.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam(required = false) UUID id,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) LocalDateTime initialDate,
                                                       @RequestParam(required = false) LocalDateTime endDate,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "25") int limit,
                                                       @RequestParam(defaultValue = "id") String columnName,
                                                       @RequestParam(defaultValue = "DESC") OrderEnumRequest order) {
        log.info("Entering the get posts flow.");
        var request = new GetPostsRequest(id, title, initialDate, endDate, page, limit, columnName, order);
        var response = postService.getPosts(request);
        log.info("Finishing the get posts flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable UUID postId,
                                                   @Valid @RequestBody CreatePostRequest request) {
        log.info("Entering the update post flow.");
        var response = postService.updatePost(postId, request.title(), request.description(), request.commentaries());
        log.info("Finishing the update post flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
