package com.blog.postservice.infrastructure.responses;

import java.time.LocalDateTime;

public record PostResponse(String title, String description, LocalDateTime createdAt) {
}
