package com.blog.commentaryservice.mocks;

import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.domain.entities.Post;
import com.blog.commentaryservice.infrastructure.requests.CreateCommentaryRequest;
import com.blog.commentaryservice.infrastructure.requests.GetCommentariesRequest;
import com.blog.commentaryservice.infrastructure.requests.OrderEnumRequest;
import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MockBuilder {

    public static Commentary createCommentary() {
        return new Commentary(
                MockBuilder.buildUUIDFromString(),
                "Description mocked from the test in application.",
                createLocalDateTime(),
                null
        );
    }

    public static CreateCommentaryRequest createCommentaryRequest() {
        return new CreateCommentaryRequest(
                "Description mocked from the test in application."
        );
    }

    public static CommentaryResponse createCommentaryResponse() {
        return new CommentaryResponse(
                "Description mocked from the test in application.",
                createLocalDateTime()
        );
    }

    public static Post createPost() {
        return new Post(
                MockBuilder.buildUUIDFromString(),
                "Title mock test",
                "Description mocked from the test in application.",
                createLocalDateTime(),
                List.of(new Commentary())
        );
    }

    public static GetCommentariesRequest createGetCommentaryRequest() {
        return new GetCommentariesRequest(
                MockBuilder.buildUUIDFromString(),
                0,
                25,
                "id",
                OrderEnumRequest.DESC
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

    public static UUID buildUUIDFromString() {
        return UUID.fromString("64aad137-45eb-46f5-ac45-0919563886b2");
    }

    public static Specification<Commentary> createCommentarySpecification() {
        return Specification.where(null);
    }

    public static Pageable createPageable() {
        return PageRequest.of(0, 25, Sort.Direction.DESC, "id");
    }

    public static Page<Commentary> createCommentaryPost() {
        return Page.empty();
    }
}
