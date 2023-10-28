package com.blog.postservice.domain.entities;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentaryTest {

    @InjectMocks
    private Commentary commentary;

    @Test
    void shouldCreateCommentaryObject() {
        var response = MockBuilder.createCommentary();
        response.setId(MockBuilder.buildUUIDFromString());
        response.setCreatedAt(MockBuilder.createLocalDateTime());
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
        Assertions.assertThat(response.getDescription()).isNotNull();
        Assertions.assertThat(response.getPost()).isNotNull();
        Assertions.assertThat(response.getCreatedAt()).isNotNull();
    }
}
