package com.blog.postservice.adapters.mappers;

import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.infrastructure.responses.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostResponse fromPostEntity(Post post);
}
