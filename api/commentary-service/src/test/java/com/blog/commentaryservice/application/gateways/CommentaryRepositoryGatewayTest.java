package com.blog.commentaryservice.application.gateways;

import com.blog.commentaryservice.adapters.persistence.CommentaryRepository;
import com.blog.commentaryservice.adapters.persistence.PostRepository;
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
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CommentaryRepositoryGatewayTest {

    @InjectMocks
    private CommentaryRepositoryGateway commentaryRepositoryGateway;

    @Mock
    private CommentaryRepository commentaryRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldCreatePostWhen_SendTitleAndDescription() {
        var commentary = new Commentary();
        var commentaryResponse = MockBuilder.createCommentary();
        Mockito.when(commentaryRepository.save(commentary)).thenReturn(commentaryResponse);
        var response = commentaryRepositoryGateway.createCommentary(null, null);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldGetCommentariesWhen_SendRequest() {
        var specifications = MockBuilder.createCommentarySpecification();
        var pageable = MockBuilder.createPageable();
        var pagePost = MockBuilder.createCommentaryPost();
        Mockito.when(commentaryRepository.findAll(specifications, pageable)).thenReturn(pagePost);
        var response = commentaryRepositoryGateway.getCommentary(specifications, pageable);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldUpdateCommentary() {
        var uuid = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        var commentary = MockBuilder.createCommentary();
        Mockito.when(commentaryRepository.findById(uuid)).thenReturn(Optional.of(commentary));
        Mockito.when(commentaryRepository.save(commentary)).thenReturn(commentary);
        var response = commentaryRepositoryGateway.updateCommentary(uuid, uuid, description);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldCustomExceptionWhen_CommentaryNotFound() {
        var uuid = MockBuilder.buildUUIDFromString();
        var description = "Description mocked from the test in application.";
        Mockito.when(commentaryRepository.findById(uuid)).thenReturn(Optional.empty());
        var customException = Assertions.catchThrowable(() -> {
            commentaryRepositoryGateway.updateCommentary(uuid, uuid, description);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldThrowDeleteCommentaryWhen_SendPostId() {
        var id = MockBuilder.buildUUIDFromString();
        Mockito.when(commentaryRepository.existsById(id)).thenReturn(false);
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.deleteCommentary(id, id)
        );
        Mockito.verify(commentaryRepository, Mockito.never()).deleteById(id);
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldDeletePostWhen_SendCommentaryId() {
        var id = MockBuilder.buildUUIDFromString();
        Mockito.when(commentaryRepository.existsById(id)).thenReturn(true);
        commentaryRepositoryGateway.deleteCommentary(id, id);
        Mockito.verify(commentaryRepository).deleteById(id);
    }
}
