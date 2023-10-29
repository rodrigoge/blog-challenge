package com.blog.commentaryservice.core.cases;

import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;

import java.util.UUID;

public interface CreateCommentaryUseCase {

    CommentaryResponse createCommentary(UUID postId, String description);
}
