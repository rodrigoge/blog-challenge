package com.blog.postservice.application.gateways;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryGatewayTest {

    @InjectMocks
    private PostRepositoryGateway postRepositoryGateway;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldCreatePostWhen_SendTitleAndDescription() {
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPost();
        Mockito.when(postRepository.save(post)).thenReturn(postResponse);
        var response = postRepositoryGateway.createPost(title, description);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldGetPostsWhen_SendRequest() {
        var specifications = MockBuilder.createPostSpecification();
        var pageable = MockBuilder.createPageable();
        var pagePost = MockBuilder.createPagePost();
        Mockito.when(postRepository.findAll(specifications, pageable)).thenReturn(pagePost);
        var response = postRepositoryGateway.getPosts(specifications, pageable);
        Assertions.assertThat(response).isNotNull();
    }
}
