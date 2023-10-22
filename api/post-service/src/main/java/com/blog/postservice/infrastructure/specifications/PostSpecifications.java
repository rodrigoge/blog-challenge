package com.blog.postservice.infrastructure.specifications;

import com.blog.postservice.domain.entities.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
public class PostSpecifications {

    public static Specification<Post> hasId(UUID id) {
        log.info("Entering in the has id filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Post> hasTitle(String title) {
        log.info("Entering in the has title filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Post> hasCreatedAt(LocalDateTime initialDate, LocalDateTime endDate) {
        log.info("Entering in the has createdAt filter.");
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdAt"), initialDate, endDate);
    }
}
