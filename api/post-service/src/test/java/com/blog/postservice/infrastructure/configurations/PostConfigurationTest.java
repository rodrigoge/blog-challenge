package com.blog.postservice.infrastructure.configurations;

import com.blog.postservice.adapters.persistence.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostConfigurationTest {

    @InjectMocks
    private PostConfiguration postConfiguration;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldPostRepositoryGatewayConfiguration() {
        var response = postConfiguration.postRepositoryGateway(postRepository);
        Assertions.assertThat(response).isNotNull();
    }
}
