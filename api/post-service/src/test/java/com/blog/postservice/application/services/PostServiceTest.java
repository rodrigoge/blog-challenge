package com.blog.postservice.application.services;

import com.blog.postservice.adapters.mappers.PostMapper;
import com.blog.postservice.application.gateways.PostRepositoryGateway;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;

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
    void shouldGetPostsWhen_SendRequest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var request = MockBuilder.createGetPostRequest();
        var specifications = MockBuilder.createPostSpecification();
        var pageable = MockBuilder.createPageable();
        var pagePosts = MockBuilder.createPagePost();
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPostResponse();
        Mockito.when(postRepositoryGateway.getPosts(specifications, pageable)).thenReturn(pagePosts);
        Mockito.when(postMapper.fromPostEntity(post)).thenReturn(postResponse);
        ReflectionTestUtils.invokeMethod(postService, "buildSpecifications", request);
        ReflectionTestUtils.invokeMethod(postService, "buildPageable", request);
        ReflectionTestUtils.invokeMethod(postService, "buildMapper", pagePosts);
        var response = postService.getPosts(request);
        Assertions.assertThat(response).isNotNull();
    }
}
