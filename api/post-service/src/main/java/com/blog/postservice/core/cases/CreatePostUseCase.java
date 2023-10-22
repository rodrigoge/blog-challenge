package com.blog.postservice.core.cases;

import com.blog.postservice.domain.entities.Post;

public interface CreatePostUseCase {

    Post createPost(String title, String description);
}
