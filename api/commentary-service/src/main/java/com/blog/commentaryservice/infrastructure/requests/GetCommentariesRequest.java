package com.blog.commentaryservice.infrastructure.requests;

import java.util.UUID;

public record GetCommentariesRequest(
        UUID postId,
        int page,
        int limit,
        String columnName,
        OrderEnumRequest order) {
}
