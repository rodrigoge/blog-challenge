package com.blog.postservice.application.services;

import com.blog.postservice.adapters.mappers.PostMapper;
import com.blog.postservice.application.gateways.PostRepositoryGateway;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepositoryGateway postRepositoryGateway;

    @Mock
    private PostMapper postMapper;

    @Test
    void shouldCreatePostObjectWhen_SendingToService() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPostResponse();
        Mockito.when(postRepositoryGateway.createPost(title, description)).thenReturn(post);
        Mockito.when(postMapper.fromPostEntity(post)).thenReturn(postResponse);
        var response = postService.createPost(title, description);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.title()).isEqualTo(title);
        Assertions.assertThat(response.description()).isEqualTo(description);
    }

    @Test
    void shouldCustomExceptionWhen_SendingNullObject() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        Mockito.when(postRepositoryGateway.createPost(title, description)).thenReturn(null);
        var customException = Assertions.catchThrowable(() -> {
            postService.createPost(title, description);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldGetPostsWhen_SendRequest() {
        var request = MockBuilder.createGetPostRequest();
        Page<Post> samplePage = new PageImpl<>(new ArrayList<>());
        Mockito.when(postRepositoryGateway.getPosts(Mockito.any(), Mockito.any())).thenReturn(samplePage);
        postService.getPosts(request);
        Mockito.verify(postRepositoryGateway).getPosts(Mockito.any(), Mockito.any());
    }

    @Test
    void shouldReturnCustomExceptionWhen_InvalidDates() throws NoSuchMethodException {
        Class<?> clazz = postService.getClass();
        Method validateMethod = clazz.getDeclaredMethod("validateInitialAndEndDate", LocalDateTime.class, LocalDateTime.class);
        validateMethod.setAccessible(true);
        var initialDate = LocalDateTime.of(2023, 1, 1, 12, 0);
        var endDate = LocalDateTime.of(2022, 12, 31, 12, 0);
        var exception = org.junit.jupiter.api.Assertions.assertThrows(CustomException.class, () -> {
            try {
                validateMethod.invoke(postService, initialDate, endDate);
            } catch (Exception e) {
                throw e.getCause();
            }
        });
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Error because the initial date is after the end date.");
    }

    @Test
    void shouldUpdatePostObjectWhen_SendingToService() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPostResponse();
        Mockito.when(postRepositoryGateway.updatePost(uuid, title, description, commentaries)).thenReturn(post);
        Mockito.when(postMapper.fromPostEntity(post)).thenReturn(postResponse);
        var response = postService.updatePost(uuid, title, description, commentaries);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.title()).isEqualTo(title);
        Assertions.assertThat(response.description()).isEqualTo(description);
    }

    @Test
    void shouldCustomExceptionWhen_NotFoundPostToUpdate() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        Mockito.when(postRepositoryGateway.updatePost(uuid, title, description, commentaries)).thenReturn(null);
        var customException = Assertions.catchThrowable(() -> {
            postService.updatePost(uuid, title, description, commentaries);
        });
        Assertions.assertThat(customException).isNotNull();
    }

    @Test
    void shouldDeletePostWhen_SendPostId() {
        var uuid = MockBuilder.buildUUIDFromString();
        postService.deletePost(uuid);
        Mockito.verify(postRepositoryGateway).deletePost(uuid);
    }
}
