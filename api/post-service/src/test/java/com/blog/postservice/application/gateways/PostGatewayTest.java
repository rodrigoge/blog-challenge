package com.blog.postservice.application.gateways;

import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

    @Test
    void shouldTestUpdatePostGateway() {
        var post = MockBuilder.createPost();
        var uuid = MockBuilder.buildUUIDFromString();
        var title = post.getTitle();
        var description = post.getDescription();
        var commentaries = List.of(new Commentary());
        Mockito.when(postGateway.updatePost(uuid, title, description, commentaries)).thenReturn(post);
        var postGatewayResponse = postGateway.updatePost(uuid, title, description, commentaries);
        Assertions.assertThat(postGatewayResponse).isNotNull();
    }

    @Test
    void shouldTestDeletePostGateway() {
        var uuid = MockBuilder.buildUUIDFromString();
        Mockito.doNothing().when(postGateway).deletePost(uuid);
        postGateway.deletePost(uuid);
        Mockito.verify(postGateway, Mockito.times(1)).deletePost(uuid);
    }
}
