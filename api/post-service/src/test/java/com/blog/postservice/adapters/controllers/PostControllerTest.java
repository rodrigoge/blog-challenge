package com.blog.postservice.adapters.controllers;

import com.blog.postservice.application.services.PostService;
import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.infrastructure.requests.OrderEnumRequest;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;


    @Test
    void shouldCreatePostWhen_SendRequest() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var date = MockBuilder.createLocalDateTime();
        var request = MockBuilder.createPostRequest();
        var createPostResponse = MockBuilder.createPostResponse();
        Mockito.when(postService.createPost(title, description)).thenReturn(createPostResponse);
        var response = postController.createPost(request);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().title()).isEqualTo(title);
        Assertions.assertThat(response.getBody().description()).isEqualTo(description);
        Assertions.assertThat(response.getBody().createdAt()).isEqualTo(date);
    }

    @Test
    void shouldGetPostWhen_SendRequest() {
        var id = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var initialDate = MockBuilder.createLocalDateTime();
        var endDate = MockBuilder.createLocalDateTime();
        var page = 0;
        var limit = 25;
        var columnName = "title";
        var order = OrderEnumRequest.DESC;
        var request = MockBuilder.createGetPostRequest();
        var postsResponse = MockBuilder.createPostResponse();
        Mockito.when(postService.getPosts(request)).thenReturn(List.of(postsResponse));
        var response = postController.getPosts(
                id,
                title,
                initialDate,
                endDate,
                page,
                limit,
                columnName,
                order
        );
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldUpdatePostWhen_SendRequest() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        var date = MockBuilder.createLocalDateTime();
        var request = MockBuilder.createPostRequest();
        var createPostResponse = MockBuilder.createPostResponse();
        Mockito.when(postService.updatePost(uuid, title, description, commentaries)).thenReturn(createPostResponse);
        var response = postController.updatePost(uuid, request);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().title()).isEqualTo(title);
        Assertions.assertThat(response.getBody().description()).isEqualTo(description);
        Assertions.assertThat(response.getBody().createdAt()).isEqualTo(date);
    }

    @Test
    void shouldDeletePostWhen_SendRequest() {
        var uuid = MockBuilder.buildUUIDFromString();
        postController.deletePost(uuid);
        Mockito.verify(postService).deletePost(uuid);
    }
}
