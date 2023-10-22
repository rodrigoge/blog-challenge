package com.blog.postservice.application.gateways;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.domain.entities.Post;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PostRepositoryGateway implements PostGateway {

    private final PostRepository postRepository;

    public PostRepositoryGateway(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(String title, String description) {
        log.info("Saving the post in the database.");
        var post = new Post(title, description);
        return postRepository.save(post);
    }
}
