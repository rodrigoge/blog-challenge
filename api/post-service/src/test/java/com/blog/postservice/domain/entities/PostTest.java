package com.blog.postservice.domain.entities;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PostTest {

    @InjectMocks
    private Post post;

    @Test
    void shouldCreatePostObject() {
        var response = MockBuilder.createPost();
        response.setId(MockBuilder.buildUUIDFromString());
        response.setCreatedAt(MockBuilder.createLocalDateTime());
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
        Assertions.assertThat(response.getTitle()).isNotNull();
        Assertions.assertThat(response.getDescription()).isNotNull();
        Assertions.assertThat(response.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldCreatePostObjectWhen_EmptyConstructor() {
        var response = new Post();
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldCreatePostObjectWhen_PostConstructor() {
        var response = new Post(
                MockBuilder.buildUUIDFromString(),
                "Title from test.",
                "Description from test."
        );
        Assertions.assertThat(response).isNotNull();
    }
}
