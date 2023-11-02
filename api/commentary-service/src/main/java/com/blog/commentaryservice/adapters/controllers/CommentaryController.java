package com.blog.commentaryservice.adapters.controllers;

import com.blog.commentaryservice.application.services.CommentaryService;
import com.blog.commentaryservice.infrastructure.requests.CreateCommentaryRequest;
import com.blog.commentaryservice.infrastructure.requests.GetCommentariesRequest;
import com.blog.commentaryservice.infrastructure.requests.OrderEnumRequest;
import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commentaries/{postId}")
@Log4j2
public class CommentaryController {

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @PostMapping
    public ResponseEntity<CommentaryResponse> createCommentary(@PathVariable UUID postId,
                                                               @Valid @RequestBody CreateCommentaryRequest request) {
        log.info("Entering the create commentary flow.");
        var response = commentaryService.createCommentary(postId, request.description());
        log.info("Finishing the create commentary flow.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentaryResponse>> getCommentaries(@PathVariable UUID postId,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "25") int limit,
                                                                    @RequestParam(defaultValue = "id") String columnName,
                                                                    @RequestParam(defaultValue = "DESC") OrderEnumRequest order) {
        log.info("Entering the get commentaries flow.");
        var request = new GetCommentariesRequest(postId, page, limit, columnName, order);
        var response = commentaryService.getCommentaries(request);
        log.info("Finishing the get commentaries flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{commentaryId}")
    public ResponseEntity<CommentaryResponse> updateCommentary(@PathVariable UUID postId,
                                                               @PathVariable UUID commentaryId,
                                                               @Valid @RequestBody CreateCommentaryRequest request) {
        log.info("Entering the update commentary flow.");
        var response = commentaryService.updateCommentary(postId, commentaryId, request.description());
        log.info("Finishing the update commentary flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{commentaryId}")
    public ResponseEntity<Void> deleteCommentary(@PathVariable UUID postId, @PathVariable UUID commentaryId) {
        log.info("Entering the delete commentary flow.");
        commentaryService.deleteCommentary(postId, commentaryId);
        log.info("Finishing the delete commentary flow");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
