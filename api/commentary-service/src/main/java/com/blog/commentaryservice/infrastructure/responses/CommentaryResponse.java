package com.blog.commentaryservice.infrastructure.responses;

import java.time.LocalDateTime;

public record CommentaryResponse(String description, LocalDateTime createdAt) {
}
