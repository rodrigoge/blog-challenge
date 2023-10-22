package com.blog.postservice.application.gateways;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostGatewayTest {

    @Mock
    private PostGateway postGateway;

    @Test
    void shouldTestPostGateway() {
        var post = MockBuilder.createPost();
        var title = post.getTitle();
        var description = post.getDescription();
        Mockito.when(postGateway.createPost(title, description)).thenReturn(post);
        var postGatewayResponse = postGateway.createPost(title, description);
        Assertions.assertThat(postGatewayResponse).isNotNull();
    }

    @Test
    void shouldTestGetPostsGateway() {
        var specification = MockBuilder.createPostSpecification();
        var pageable = MockBuilder.createPageable();
        var postPage = MockBuilder.createPagePost();
        Mockito.when(postGateway.getPosts(specification, pageable)).thenReturn(postPage);
        var response = postGateway.getPosts(specification, pageable);
        Assertions.assertThat(response).isNotNull();
    }
}
