package com.blog.commentaryservice.adapters.persistence;

import com.blog.commentaryservice.domain.entities.Commentary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentaryRepository extends JpaRepository<Commentary, UUID> {

    Page<Commentary> findAll(Specification<Commentary> specification, Pageable pageable);
}
