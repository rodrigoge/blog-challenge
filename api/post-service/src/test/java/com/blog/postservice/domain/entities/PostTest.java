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
        response.setId(UUID.fromString("64aad137-45eb-46f5-ac45-0919563886b2"));
        response.setCreatedAt(MockBuilder.createLocalDateTime());
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
        Assertions.assertThat(response.getTitle()).isNotNull();
        Assertions.assertThat(response.getDescription()).isNotNull();
        Assertions.assertThat(response.getCreatedAt()).isNotNull();
    }
}
