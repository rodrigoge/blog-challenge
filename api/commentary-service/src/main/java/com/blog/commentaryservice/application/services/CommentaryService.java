package com.blog.commentaryservice.application.services;

import com.blog.commentaryservice.adapters.mappers.CommentaryMapper;
import com.blog.commentaryservice.application.gateways.CommentaryRepositoryGateway;
import com.blog.commentaryservice.core.cases.CreateCommentaryUseCase;
import com.blog.commentaryservice.core.cases.DeleteCommentaryUseCase;
import com.blog.commentaryservice.core.cases.GetCommentariesUseCase;
import com.blog.commentaryservice.core.cases.UpdateCommentaryUseCase;
import com.blog.commentaryservice.core.exceptions.CustomException;
import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.infrastructure.requests.GetCommentariesRequest;
import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;
import com.blog.commentaryservice.infrastructure.specifications.CommentarySpecifications;
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
public class CommentaryService implements
        CreateCommentaryUseCase,
        GetCommentariesUseCase,
        UpdateCommentaryUseCase,
        DeleteCommentaryUseCase {

    private final CommentaryRepositoryGateway commentaryRepositoryGateway;
    private final CommentaryMapper commentaryMapper;

    @Autowired
    public CommentaryService(CommentaryRepositoryGateway commentaryRepositoryGateway, CommentaryMapper commentaryMapper) {
        this.commentaryRepositoryGateway = commentaryRepositoryGateway;
        this.commentaryMapper = commentaryMapper;
    }

    @Override
    public CommentaryResponse createCommentary(UUID postId, String description) {
        log.info("Sending the content to be saved in the base.");
        var postCreated = commentaryRepositoryGateway.createCommentary(description, postId);
        if (postCreated == null) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error while the commentary was saved."
            );
        }
        var response = commentaryMapper.fromCommentaryEntity(postCreated);
        log.info("Sending the content to be mapping.");
        return response;
    }

    @Override
    public List<CommentaryResponse> getCommentaries(GetCommentariesRequest request) {
        log.info("Sending request to be founded in the base.");
        var specifications = buildSpecifications(request);
        var pageable = buildPageable(request);
        var pagedPosts = commentaryRepositoryGateway.getCommentary(specifications, pageable);
        var response = buildMapper(pagedPosts);
        log.info("Sending the content to be mapping.");
        return response;
    }

    private Specification<Commentary> buildSpecifications(GetCommentariesRequest request) {
        log.info("Initializing the build specifications.");
        Specification<Commentary> specifications = Specification.where(null);
        specifications = specifications.and(CommentarySpecifications.hasPostId(request.postId()));
        log.info("Finishing the build specifications.");
        return specifications;
    }

    private Pageable buildPageable(GetCommentariesRequest request) {
        log.info("Initializing the build pageable.");
        var page = request.page();
        var limit = request.limit();
        var order = buildSortOrder(request);
        var sort = Sort.by(order, request.columnName());
        log.info("Finishing the build pageable.");
        return PageRequest.of(page, limit, sort);
    }

    private List<CommentaryResponse> buildMapper(Page<Commentary> posts) {
        log.info("Executing the mapping response.");
        return posts
                .stream()
                .map(commentaryMapper::fromCommentaryEntity)
                .collect(Collectors.toList());
    }

    private Sort.Direction buildSortOrder(GetCommentariesRequest request) {
        log.info("Executing the ordering response.");
        return request.order().getDescription()
                .equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
    }

    @Override
    public CommentaryResponse updateCommentary(UUID postId, UUID commentaryId, String description) {
        log.info("Sending the content to be updated in the base.");
        var postFounded = commentaryRepositoryGateway.updateCommentary(postId, commentaryId, description);
        if (postFounded == null) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now(),
                    "Error while the commentary was updated."
            );
        }
        var response = commentaryMapper.fromCommentaryEntity(postFounded);
        log.info("Sending the content to be mapping.");
        return response;
    }

    @Override
    public void deleteCommentary(UUID postId, UUID commentaryId) {
        log.info("Sending the content to be deleted in the base.");
        commentaryRepositoryGateway.deleteCommentary(postId, commentaryId);
    }
}
