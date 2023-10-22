package com.blog.postservice.mocks;

import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.infrastructure.requests.CreatePostRequest;
import com.blog.postservice.infrastructure.responses.CreatePostResponse;

import java.time.LocalDateTime;

public class MockBuilder {

    public static Post createPost() {
        return new Post(
                "Title mock test",
                "Description mocked from the test in application."
        );
    }

    public static CreatePostRequest createPostRequest() {
        return new CreatePostRequest(
                "Title mock test",
                "Description mocked from the test in application."
        );
    }

    public static CreatePostResponse createPostResponse() {
        var date = createLocalDateTime();
        return new CreatePostResponse(
                "Title mock test",
                "Description mocked from the test in application.",
                date
        );
    }

    public static LocalDateTime createLocalDateTime() {
        return LocalDateTime.of(
                2023,
                10,
                9,
                10,
                30,
                0
        );
    }
}
