package com.blog.commentaryservice.application.gateways;

import com.blog.commentaryservice.adapters.persistence.CommentaryRepository;
import com.blog.commentaryservice.adapters.persistence.PostRepository;
import com.blog.commentaryservice.core.exceptions.CustomException;
import com.blog.commentaryservice.domain.entities.Commentary;
import com.blog.commentaryservice.domain.entities.Post;
import com.blog.commentaryservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
    void shouldCreateNewCommentary() {
        var postId = UUID.randomUUID();
        var description = "Test commentary";
        var post = new Post();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        var savedCommentary = new Commentary(description, post);
        Mockito.when(commentaryRepository.save(Mockito.any(Commentary.class))).thenReturn(savedCommentary);
        var createdCommentary = commentaryRepositoryGateway.createCommentary(description, postId);
        assertNotNull(createdCommentary);
        assertEquals(description, createdCommentary.getDescription());
        assertSame(post, createdCommentary.getPost());
        Mockito.verify(commentaryRepository, Mockito.times(1)).save(Mockito.any(Commentary.class));
    }

    @Test
    void shouldErrorOnCreateNewCommentaryWhen_PostNonExists() {
        var postId = UUID.randomUUID();
        var description = "Test commentary";
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.createCommentary(description, postId)
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error because the comment cannot be saved on a non-existent post.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).save(Mockito.any(Commentary.class));
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
    void shouldUpdateExistentCommentary() {
        var postId = MockBuilder.buildUUIDFromString();
        var commentaryId = UUID.randomUUID();
        var description = "Updated commentary";
        var post = MockBuilder.createPost();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        var existingCommentary = new Commentary(commentaryId, "Old commentary", LocalDateTime.now(), post);
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.of(existingCommentary));
        commentaryRepositoryGateway.updateCommentary(post.getId(), existingCommentary.getId(), description);
        Mockito.verify(commentaryRepository, Mockito.times(1)).save(Mockito.any(Commentary.class));
    }

    @Test
    void shouldErrorOnUpdateCommentaryWithNonExistentPost() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        var description = "Updated commentary";
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.updateCommentary(postId, commentaryId, description)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error because the comment cannot be saved on a non-existent post.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).save(Mockito.any(Commentary.class));
    }

    @Test
    void shouldUpdateCommentaryWithNonExistentCommentary() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        var description = "Updated commentary";
        var post = new Post();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.empty());
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.updateCommentary(postId, commentaryId, description)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error because the commentary is not found in the database.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).save(Mockito.any(Commentary.class));
    }

    @Test
    void shouldUpdateCommentaryWithMismatchedPost() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        var description = "Updated commentary";
        var post = MockBuilder.createPost();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        var existingCommentary = new Commentary(
                commentaryId,
                "Old commentary",
                LocalDateTime.now(),
                post
        );
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.of(existingCommentary));
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.updateCommentary(postId, commentaryId, description)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error on update commentary in the database.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).save(Mockito.any(Commentary.class));
    }

    @Test
    void shouldDeleteCommentary() {
        var postId = MockBuilder.buildUUIDFromString();
        var commentaryId = UUID.randomUUID();
        var post = MockBuilder.createPost();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        var existingCommentary = new Commentary(
                commentaryId,
                "Test commentary",
                LocalDateTime.now(),
                post
        );
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.of(existingCommentary));
        commentaryRepositoryGateway.deleteCommentary(postId, commentaryId);
        Mockito.verify(commentaryRepository, Mockito.times(1)).deleteById(commentaryId);
    }

    @Test
    void shouldDeleteCommentaryWithNonExistentPost() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.deleteCommentary(postId, commentaryId)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals(
                "Error because the comment cannot be saved on a non-existent post.",
                exception.getMessage()
        );
        Mockito.verify(commentaryRepository, Mockito.never()).deleteById(Mockito.any(UUID.class));
    }

    @Test
    void shouldDeleteCommentaryWithNonExistentCommentary() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        var post = new Post();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.empty());
        var exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.deleteCommentary(postId, commentaryId)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error because the commentary is not found in the database.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).deleteById(Mockito.any(UUID.class));
    }

    @Test
    void shouldDeleteCommentaryWithMismatchedPost() {
        var postId = UUID.randomUUID();
        var commentaryId = UUID.randomUUID();
        var post = MockBuilder.createPost();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        var existingCommentary = new Commentary(
                commentaryId,
                "Test commentary",
                LocalDateTime.now(),
                post
        );
        Mockito.when(commentaryRepository.findById(commentaryId)).thenReturn(Optional.of(existingCommentary));
        CustomException exception = assertThrows(
                CustomException.class,
                () -> commentaryRepositoryGateway.deleteCommentary(postId, commentaryId)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertNotNull(exception.getDateTime());
        assertEquals("Error on update commentary in the database.", exception.getMessage());
        Mockito.verify(commentaryRepository, Mockito.never()).deleteById(Mockito.any(UUID.class));
    }
}
