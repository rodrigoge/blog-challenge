package com.blog.commentaryservice.application.services;

import com.blog.commentaryservice.adapters.mappers.CommentaryMapper;
import com.blog.commentaryservice.application.gateways.CommentaryRepositoryGateway;
import com.blog.commentaryservice.core.exceptions.CustomException;
import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class CommentaryServiceTest {

    @InjectMocks
    private CommentaryService commentaryService;

    @Mock
    private CommentaryRepositoryGateway commentaryRepositoryGateway;

    @Mock
    private CommentaryMapper commentaryMapper;

    @Test
    void shouldCreateCommentaryObjectWhen_SendingToService() {
        var id = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        var commentary = MockBuilder.createCommentary();
        var commentaryResponse = MockBuilder.createCommentaryResponse();
        Mockito.when(commentaryRepositoryGateway.createCommentary(description, id)).thenReturn(commentary);
        Mockito.when(commentaryMapper.fromCommentaryEntity(commentary)).thenReturn(commentaryResponse);
        var response = commentaryService.createCommentary(id, description);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.description()).isEqualTo(description);
    }

    @Test
    void shouldCustomExceptionWhen_SendingNullObject() {
        var id = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        Mockito.when(commentaryRepositoryGateway.createCommentary(description, id)).thenReturn(null);
        var customException = Assertions.catchThrowable(() -> {
            commentaryService.createCommentary(id, description);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldGetCommentariesWhen_SendRequest() {
        var request = MockBuilder.createGetCommentaryRequest();
        Page<Commentary> samplePage = new PageImpl<>(new ArrayList<>());
        Mockito.when(commentaryRepositoryGateway.getCommentary(Mockito.any(), Mockito.any())).thenReturn(samplePage);
        commentaryService.getCommentaries(request);
        Mockito.verify(commentaryRepositoryGateway).getCommentary(Mockito.any(), Mockito.any());
    }

    @Test
    void shouldUpdateCommentaryObjectWhen_SendingToService() {
        var uuid = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        var commentary = MockBuilder.createCommentary();
        var commentaryResponse = MockBuilder.createCommentaryResponse();
        Mockito.when(commentaryRepositoryGateway.updateCommentary(uuid, uuid, description)).thenReturn(commentary);
        Mockito.when(commentaryMapper.fromCommentaryEntity(commentary)).thenReturn(commentaryResponse);
        var response = commentaryService.updateCommentary(uuid, uuid, description);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.description()).isEqualTo(description);
    }

    @Test
    void shouldCustomExceptionWhen_NotFoundCommentaryToUpdate() {
        var uuid = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        Mockito.when(commentaryRepositoryGateway.updateCommentary(uuid, uuid, description)).thenReturn(null);
        var customException = Assertions.catchThrowable(() -> {
            commentaryService.updateCommentary(uuid, uuid, description);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldDeleteCommentaryWhen_SendPostId() {
        var uuid = MockBuilder.buildUUIDFromString();
        commentaryService.deleteCommentary(uuid, uuid);
        Mockito.verify(commentaryRepositoryGateway).deleteCommentary(uuid, uuid);
    }
}
