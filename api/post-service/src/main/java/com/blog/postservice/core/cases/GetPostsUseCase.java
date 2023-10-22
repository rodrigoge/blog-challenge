package com.blog.postservice.core.cases;

import com.blog.postservice.infrastructure.requests.GetPostsRequest;
import com.blog.postservice.infrastructure.responses.PostResponse;

import java.util.List;

public interface GetPostsUseCase {

    List<PostResponse> getPosts(GetPostsRequest request);
}
