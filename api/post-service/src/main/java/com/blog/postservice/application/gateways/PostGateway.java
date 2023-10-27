package com.blog.postservice.application.gateways;

import com.blog.postservice.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface PostGateway {

    Post createPost(String title, String description);

    Page<Post> getPosts(Specification<Post> specifications, Pageable pageable);

    Post updatePost(UUID postId, String title, String description);
}
