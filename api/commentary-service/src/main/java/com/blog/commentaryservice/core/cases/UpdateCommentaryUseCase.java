package com.blog.commentaryservice.core.cases;

import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;

import java.util.UUID;

public interface UpdateCommentaryUseCase {

    CommentaryResponse updateCommentary(UUID postId, UUID commentaryId, String description);
}

