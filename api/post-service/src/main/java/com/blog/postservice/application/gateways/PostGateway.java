package com.blog.postservice.application.gateways;

import com.blog.postservice.domain.entities.Post;

public interface PostGateway {

    Post createPost(String title, String description);
}
