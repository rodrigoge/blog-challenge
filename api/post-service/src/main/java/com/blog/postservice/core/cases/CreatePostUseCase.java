package com.blog.postservice.core.cases;

import com.blog.postservice.infrastructure.responses.PostResponse;

public interface CreatePostUseCase {

    PostResponse createPost(String title, String description);
}
