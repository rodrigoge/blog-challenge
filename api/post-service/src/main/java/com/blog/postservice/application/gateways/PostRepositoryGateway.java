package com.blog.postservice.application.gateways;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.core.exceptions.CustomException;
import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.domain.entities.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @Override
    public Page<Post> getPosts(Specification<Post> specifications, Pageable pageable) {
        log.info("Getting the posts in the database.");
        return postRepository.findAll(specifications, pageable);
    }

    @Override
    public Post updatePost(UUID postId, String title, String description, List<Commentary> commentaries) {
        log.info("Updating the post in the database.");
        var postFounded = postRepository.findById(postId);
        if (postFounded.isEmpty()) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because post is not found in the database."
            );
        }
        var id = postFounded.get().getId();
        var createdAt = postFounded.get().getCreatedAt();
        var post = new Post(id, title, description, createdAt, commentaries);
        return postRepository.save(post);
    }
}
