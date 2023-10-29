package com.blog.commentaryservice.core.cases;

import java.util.UUID;

public interface DeleteCommentaryUseCase {

    void deleteCommentary(UUID postId, UUID commentaryId);
}
