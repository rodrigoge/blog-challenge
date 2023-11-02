package com.blog.commentaryservice.infrastructure.specifications;

import com.blog.commentaryservice.domain.entities.Commentary;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@Log4j2
public class CommentarySpecifications {

    public static Specification<Commentary> hasPostId(UUID postId) {
        log.info("Entering in the has post id filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("post").get("id"), postId);
    }
}
