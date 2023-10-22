package com.blog.postservice.infrastructure.requests;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePostRequestTest {

    @InjectMocks
    private CreatePostRequest createPostRequest;

    @Test
    void shouldCreatePostRequest() {
        var response = MockBuilder.createPostRequest();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.title()).isNotNull();
        Assertions.assertThat(response.description()).isNotNull();
    }
}
