package com.blog.postservice.infrastructure.responses;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostResponseTest {

    @InjectMocks
    private PostResponse postResponse;

    @Test
    void shouldCreatePostRequest() {
        var response = MockBuilder.createPostResponse();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.title()).isNotNull();
        Assertions.assertThat(response.description()).isNotNull();
        Assertions.assertThat(response.createdAt()).isNotNull();
    }
}
