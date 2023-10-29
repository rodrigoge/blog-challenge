package com.blog.postservice.application.gateways;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.core.exceptions.CustomException;
import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryGatewayTest {

    @InjectMocks
    private PostRepositoryGateway postRepositoryGateway;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldCreatePostWhen_SendTitleAndDescription() {
        var post = new Post();
        var postResponse = MockBuilder.createPost();
        Mockito.when(postRepository.save(post)).thenReturn(postResponse);
        var response = postRepositoryGateway.createPost(null, null);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldGetPostsWhen_SendRequest() {
        var specifications = MockBuilder.createPostSpecification();
        var pageable = MockBuilder.createPageable();
        var pagePost = MockBuilder.createPagePost();
        Mockito.when(postRepository.findAll(specifications, pageable)).thenReturn(pagePost);
        var response = postRepositoryGateway.getPosts(specifications, pageable);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldUpdatePostWhen_SendIdAndTitleAndDescription() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPost();
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.of(postResponse));
        Mockito.when(postRepository.save(post)).thenReturn(postResponse);
        var response = postRepositoryGateway.updatePost(uuid, title, description, commentaries);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldCustomExceptionWhen_PostNotFound() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.empty());
        var customException = Assertions.catchThrowable(() -> {
            postRepositoryGateway.updatePost(uuid, title, description, commentaries);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldThrowDeletePostWhen_SendPostId() {
        var postId = MockBuilder.buildUUIDFromString();
        Mockito.when(postRepository.existsById(postId)).thenReturn(false);
        var exception = assertThrows(CustomException.class, () -> postRepositoryGateway.deletePost(postId));
        Mockito.verify(postRepository, Mockito.never()).deleteById(postId);
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldDeletePostWhen_SendPostId() {
        var postId = MockBuilder.buildUUIDFromString();
        Mockito.when(postRepository.existsById(postId)).thenReturn(true);
        postRepositoryGateway.deletePost(postId);
        Mockito.verify(postRepository).deleteById(postId);
    }
}
