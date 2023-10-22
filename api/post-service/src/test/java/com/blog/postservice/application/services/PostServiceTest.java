package com.blog.postservice.application.services;

import com.blog.postservice.application.gateways.PostRepositoryGateway;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepositoryGateway postRepositoryGateway;

    @Test
    void shouldCreatePostObjectWhen_SendingToService() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var post = MockBuilder.createPost();
        Mockito.when(postRepositoryGateway.createPost(title, description)).thenReturn(post);
        var response = postService.createPost(title, description);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getTitle()).isEqualTo(title);
        Assertions.assertThat(response.getDescription()).isEqualTo(description);
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
}
