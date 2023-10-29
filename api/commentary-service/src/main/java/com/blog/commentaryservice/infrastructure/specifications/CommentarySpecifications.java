package com.blog.commentaryservice.infrastructure.specifications;

import com.blog.commentaryservice.domain.entities.Commentary;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
public class CommentarySpecifications {

    public static Specification<Commentary> hasId(UUID id) {
        log.info("Entering in the has id filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Commentary> hasDescription(String description) {
        log.info("Entering in the has title filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Commentary> hasCreatedAt(LocalDateTime initialDate, LocalDateTime endDate) {
        log.info("Entering in the has createdAt filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdAt"), initialDate, endDate);
    }

    public static Specification<Commentary> hasPostId(UUID postId) {
        log.info("Entering in the has post id filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("post").get("id"), postId);
    }
}
