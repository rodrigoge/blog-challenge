package com.blog.postservice.application.gateways;

import com.blog.postservice.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PostGateway {

    Post createPost(String title, String description);

    Page<Post> getPosts(Specification<Post> specifications, Pageable pageable);
}
