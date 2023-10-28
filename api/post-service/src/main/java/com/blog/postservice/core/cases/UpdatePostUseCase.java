package com.blog.postservice.core.cases;

import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.infrastructure.responses.PostResponse;

import java.util.List;
import java.util.UUID;

public interface UpdatePostUseCase {

    PostResponse updatePost(UUID postId, String title, String description, List<Commentary> commentaries);
}

