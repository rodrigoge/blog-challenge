package com.blog.commentaryservice.application.gateways;

import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface CommentaryGateway {

    Commentary createCommentary(String description, UUID postId);

    Page<Commentary> getCommentary(Specification<Commentary> specifications, Pageable pageable);

    Commentary updateCommentary(UUID postId, UUID commentaryId, String description);

    void deleteCommentary(UUID postId, UUID commentaryId);
}
