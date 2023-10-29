package com.blog.commentaryservice.adapters.mappers;

import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentaryMapper {

    CommentaryResponse fromCommentaryEntity(Commentary commentary);
}
