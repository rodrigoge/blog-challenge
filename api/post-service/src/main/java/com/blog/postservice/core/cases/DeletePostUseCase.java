package com.blog.postservice.core.cases;

import java.util.UUID;

public interface DeletePostUseCase {

    void deletePost(UUID postId);
}
