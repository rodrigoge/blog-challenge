package com.blog.postservice.infrastructure.requests;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetPostsRequest(
        UUID id,
        String title,
        LocalDateTime initialDate,
        LocalDateTime endDate,
        int page,
        int limit,
        String columnName,
        OrderEnumRequest order) {
}
