package com.blog.postservice.mocks;

import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.infrastructure.requests.CreatePostRequest;
import com.blog.postservice.infrastructure.requests.GetPostsRequest;
import com.blog.postservice.infrastructure.requests.OrderEnumRequest;
import com.blog.postservice.infrastructure.responses.PostResponse;
import com.blog.postservice.infrastructure.specifications.PostSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public static PostResponse createPostResponse() {
        var date = createLocalDateTime();
        return new PostResponse(
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

    public static GetPostsRequest createGetPostRequest() {
        return new GetPostsRequest(
                buildUUIDFromString(),
                "Title mock test",
                createLocalDateTime(),
                createLocalDateTime(),
                0,
                25,
                "title",
                OrderEnumRequest.DESC
        );
    }

    public static UUID buildUUIDFromString() {
        return UUID.fromString("64aad137-45eb-46f5-ac45-0919563886b2");
    }

    public static Specification<Post> createPostSpecification() {
        return Specification.where(null);
    }

    public static Pageable createPageable() {
        return PageRequest.of(0, 25, Sort.Direction.DESC, "title");
    }

    public static Page<Post> createPagePost() {
        return Page.empty();
    }
}
