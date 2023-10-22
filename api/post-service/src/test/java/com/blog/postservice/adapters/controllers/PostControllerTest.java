package com.blog.postservice.adapters.controllers;

import com.blog.postservice.adapters.mappers.PostMapper;
import com.blog.postservice.application.services.PostService;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @Mock
    private PostMapper postMapper;

    @Test
    void shouldCreatePostWhen_SendRequest() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var date = MockBuilder.createLocalDateTime();
        var post = MockBuilder.createPost();
        var request = MockBuilder.createPostRequest();
        var createPostResponse = MockBuilder.createPostResponse();
        Mockito.when(postService.createPost(title, description)).thenReturn(post);
        Mockito.when(postMapper.fromPostEntity(post)).thenReturn(createPostResponse);
        var response = postController.createPost(request);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().title()).isEqualTo(title);
        Assertions.assertThat(response.getBody().description()).isEqualTo(description);
        Assertions.assertThat(response.getBody().createdAt()).isEqualTo(date);
    }
}
