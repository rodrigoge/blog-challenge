package com.blog.postservice.core.cases;

import com.blog.postservice.infrastructure.responses.PostResponse;

import java.util.UUID;

public interface UpdatePostUseCase {

    PostResponse updatePost(UUID postId, String title, String description);
}

