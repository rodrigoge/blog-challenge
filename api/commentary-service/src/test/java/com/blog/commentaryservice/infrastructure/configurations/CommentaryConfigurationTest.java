package com.blog.commentaryservice.infrastructure.configurations;

import com.blog.commentaryservice.adapters.persistence.CommentaryRepository;
import com.blog.commentaryservice.adapters.persistence.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentaryConfigurationTest {

    @InjectMocks
    private CommentaryConfiguration commentaryConfiguration;

    @Mock
    private CommentaryRepository commentaryRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldCommentaryRepositoryGatewayConfiguration() {
        var response = commentaryConfiguration.commentaryRepositoryGateway(commentaryRepository, postRepository);
        Assertions.assertThat(response).isNotNull();
    }
}
