package com.blog.commentaryservice.core.cases;

import com.blog.commentaryservice.infrastructure.requests.GetCommentariesRequest;
import com.blog.commentaryservice.infrastructure.responses.CommentaryResponse;

import java.util.List;

public interface GetCommentariesUseCase {

    List<CommentaryResponse> getCommentaries(GetCommentariesRequest request);
}
