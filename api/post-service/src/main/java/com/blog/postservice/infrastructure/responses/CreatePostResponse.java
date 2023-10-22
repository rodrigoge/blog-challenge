package com.blog.postservice.infrastructure.responses;

import java.time.LocalDateTime;

public record CreatePostResponse(String title, String description, LocalDateTime createdAt) {
}
