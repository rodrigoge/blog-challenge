package com.blog.commentaryservice.domain.entities;

import com.blog.commentaryservice.mocks.MockBuilder;
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
        Assertions.assertThat(response).isNotNull();
    }
}
