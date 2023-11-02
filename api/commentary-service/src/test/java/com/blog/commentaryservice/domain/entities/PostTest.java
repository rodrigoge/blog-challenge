package com.blog.commentaryservice.domain.entities;

import com.blog.commentaryservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostTest {

    @InjectMocks
    private Post post;

    @Test
    void shouldCreatePostObject() {
        var response = MockBuilder.createPost();
        response.setId(MockBuilder.buildUUIDFromString());
        response.setCreatedAt(MockBuilder.createLocalDateTime());
        response.setCommentaries(List.of(MockBuilder.createCommentary()));
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
        Assertions.assertThat(response.getTitle()).isNotNull();
        Assertions.assertThat(response.getDescription()).isNotNull();
        Assertions.assertThat(response.getCreatedAt()).isNotNull();
        Assertions.assertThat(response.getCommentaries()).isNotNull();
    }

    @Test
    void shouldCreatePostObjectWhen_EmptyConstructor() {
        var response = new Post();
        Assertions.assertThat(response).isNotNull();
    }
}
