package com.blog.commentaryservice.application.gateways;

import com.blog.commentaryservice.adapters.persistence.CommentaryRepository;
import com.blog.commentaryservice.adapters.persistence.PostRepository;
import com.blog.commentaryservice.core.exceptions.CustomException;
import com.blog.commentaryservice.domain.entities.Commentary;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
public class CommentaryRepositoryGateway implements CommentaryGateway {

    private final CommentaryRepository commentaryRepository;
    private final PostRepository postRepository;

    public CommentaryRepositoryGateway(CommentaryRepository commentaryRepository,
                                       PostRepository postRepository) {
        this.commentaryRepository = commentaryRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Commentary createCommentary(String description, UUID postId) {
        log.info("Saving the commentary in the database.");
        var post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    LocalDateTime.now(),
                    "Error because the comment cannot be saved on a non-existent post."
            );
        }
        var commentary = new Commentary(description, post.get());
        return commentaryRepository.save(commentary);
    }

    @Override
    public Page<Commentary> getCommentary(Specification<Commentary> specifications,
                                          Pageable pageable) {
        log.info("Getting the commentaries in the database.");
        return commentaryRepository.findAll(specifications, pageable);
    }

    @Override
    public Commentary updateCommentary(UUID postId, UUID commentaryId, String description) {
        log.info("Updating the commentary in the database.");
        var post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because the comment cannot be saved on a non-existent post."
            );
        }
        var commentaryFounded = commentaryRepository.findById(commentaryId);
        if (commentaryFounded.isEmpty()) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because the commentary is not found in the database."
            );
        }
        if (!commentaryFounded.get().getPost().getId().equals(postId)) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error on update commentary in the database."
            );
        }
        var createdAt = commentaryFounded.get().getCreatedAt();
        var commentary = new Commentary(commentaryId, description, createdAt, post.get());
        return commentaryRepository.save(commentary);
    }

    @Override
    public void deleteCommentary(UUID postId, UUID commentaryId) {
        log.info("Deleting the post in the database.");
        var post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because the comment cannot be saved on a non-existent post."
            );
        }
        var commentaryFounded = commentaryRepository.findById(commentaryId);
        if (commentaryFounded.isEmpty()) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because the commentary is not found in the database."
            );
        }
        if (!commentaryFounded.get().getPost().getId().equals(postId)) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error on update commentary in the database."
            );
        }
        commentaryRepository.deleteById(commentaryId);
        log.info("Commentary already deleted in the database.");
    }
}
