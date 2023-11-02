package com.blog.commentaryservice.adapters.controllers;

import com.blog.commentaryservice.application.services.CommentaryService;
import com.blog.commentaryservice.infrastructure.requests.OrderEnumRequest;
import com.blog.commentaryservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CommentaryControllerTest {

    @InjectMocks
    private CommentaryController commentaryController;

    @Mock
    private CommentaryService commentaryService;


    @Test
    void shouldCreateCommentaryWhen_SendRequest() {
        var id = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        var date = MockBuilder.createLocalDateTime();
        var request = MockBuilder.createCommentaryRequest();
        var createCommentaryResponse = MockBuilder.createCommentaryResponse();
        Mockito.when(commentaryService.createCommentary(id, request.description())).thenReturn(createCommentaryResponse);
        var response = commentaryController.createCommentary(id, request);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().description()).isEqualTo(description);
        Assertions.assertThat(response.getBody().createdAt()).isEqualTo(date);
    }

    @Test
    void shouldGetCommentariesWhen_SendRequest() {
        var id = MockBuilder.buildUUIDFromString();
        var page = 0;
        var limit = 25;
        var columnName = "id";
        var order = OrderEnumRequest.DESC;
        var request = MockBuilder.createGetCommentaryRequest();
        var commentaryResponse = MockBuilder.createCommentaryResponse();
        Mockito.when(commentaryService.getCommentaries(request)).thenReturn(List.of(commentaryResponse));
        var response = commentaryController.getCommentaries(
                id,
                page,
                limit,
                columnName,
                order
        );
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldUpdateCommentaryWhen_SendRequest() {
        var uuid = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        var date = MockBuilder.createLocalDateTime();
        var request = MockBuilder.createCommentaryRequest();
        var commentaryResponse = MockBuilder.createCommentaryResponse();
        Mockito.when(commentaryService.updateCommentary(uuid, uuid, request.description())).thenReturn(commentaryResponse);
        var response = commentaryController.updateCommentary(uuid, uuid, request);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().description()).isEqualTo(description);
        Assertions.assertThat(response.getBody().createdAt()).isEqualTo(date);
    }

    @Test
    void shouldDeleteCommentaryWhen_SendRequest() {
        var uuid = MockBuilder.buildUUIDFromString();
        commentaryController.deleteCommentary(uuid, uuid);
        Mockito.verify(commentaryService).deleteCommentary(uuid, uuid);
    }
}
