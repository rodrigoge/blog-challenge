package com.blog.postservice.application.services;

import com.blog.postservice.adapters.mappers.PostMapper;
import com.blog.postservice.application.gateways.PostRepositoryGateway;
import com.blog.postservice.core.cases.CreatePostUseCase;
import com.blog.postservice.core.cases.GetPostsUseCase;
import com.blog.postservice.core.cases.UpdatePostUseCase;
import com.blog.postservice.core.exceptions.CustomException;
import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.infrastructure.requests.GetPostsRequest;
import com.blog.postservice.infrastructure.responses.PostResponse;
import com.blog.postservice.infrastructure.specifications.PostSpecifications;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PostService implements CreatePostUseCase, GetPostsUseCase, UpdatePostUseCase {

    private final PostRepositoryGateway postRepositoryGateway;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepositoryGateway postRepositoryGateway, PostMapper postMapper) {
        this.postRepositoryGateway = postRepositoryGateway;
        this.postMapper = postMapper;
    }

    @Override
    public PostResponse createPost(String title, String description) {
        log.info("Sending the content to be saved in the base.");
        var postCreated = postRepositoryGateway.createPost(title, description);
        if (postCreated == null) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error while the post was saved."
            );
        }
        var response = postMapper.fromPostEntity(postCreated);
        log.info("Sending the content to be mapping.");
        return response;
    }

    @Override
    public List<PostResponse> getPosts(GetPostsRequest request) {
        log.info("Sending request to be founded in the base.");
        var specifications = buildSpecifications(request);
        var pageable = buildPageable(request);
        var pagedPosts = postRepositoryGateway.getPosts(specifications, pageable);
        var response = buildMapper(pagedPosts);
        log.info("Sending the content to be mapping.");
        return response;
    }

    private Specification<Post> buildSpecifications(GetPostsRequest request) {
        log.info("Initializing the build specifications.");
        Specification<Post> specifications = Specification.where(null);
        if (request.id() != null) {
            specifications = specifications.and(PostSpecifications.hasId(request.id()));
        }
        if (request.title() != null) {
            specifications = specifications.and(PostSpecifications.hasTitle(request.title()));
        }
        if (request.initialDate() != null && request.endDate() != null) {
            validateInitialAndEndDate(request.initialDate(), request.endDate());
            specifications = specifications.and(PostSpecifications.hasCreatedAt(request.initialDate(), request.endDate()));
        }
        log.info("Finishing the build specifications.");
        return specifications;
    }

    private Pageable buildPageable(GetPostsRequest request) {
        log.info("Initializing the build pageable.");
        var page = request.page();
        var limit = request.limit();
        var order = buildSortOrder(request);
        var sort = Sort.by(order, request.columnName());
        log.info("Finishing the build pageable.");
        return PageRequest.of(page, limit, sort);
    }

    private List<PostResponse> buildMapper(Page<Post> posts) {
        log.info("Executing the mapping response.");
        return posts
                .stream()
                .map(postMapper::fromPostEntity)
                .collect(Collectors.toList());
    }

    private Sort.Direction buildSortOrder(GetPostsRequest request) {
        log.info("Executing the ordering response.");
        return request.order().getDescription()
                .equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
    }

    private void validateInitialAndEndDate(LocalDateTime initialDate, LocalDateTime endDate) {
        if (initialDate.isAfter(endDate)) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error because the initial date is after the end date."
            );
        }
    }

    @Override
    public PostResponse updatePost(UUID postId, String title, String description, List<Commentary> commentaries) {
        log.info("Sending the content to be update in the base.");
        var postFounded = postRepositoryGateway.updatePost(postId, title, description, commentaries);
        if (postFounded == null) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error while the post was updated."
            );
        }
        var response = postMapper.fromPostEntity(postFounded);
        log.info("Sending the content to be mapping.");
        return response;
    }
}
