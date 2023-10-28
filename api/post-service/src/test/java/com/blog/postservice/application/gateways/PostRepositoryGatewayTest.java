package com.blog.postservice.application.gateways;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.domain.entities.Commentary;
import com.blog.postservice.domain.entities.Post;
import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryGatewayTest {

    @InjectMocks
    private PostRepositoryGateway postRepositoryGateway;

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldCreatePostWhen_SendTitleAndDescription() {
        var post = new Post();
        var postResponse = MockBuilder.createPost();
        Mockito.when(postRepository.save(post)).thenReturn(postResponse);
        var response = postRepositoryGateway.createPost(null, null);
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

    @Test
    void shouldUpdatePostWhen_SendIdAndTitleAndDescription() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        var post = MockBuilder.createPost();
        var postResponse = MockBuilder.createPost();
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.of(postResponse));
        Mockito.when(postRepository.save(post)).thenReturn(postResponse);
        var response = postRepositoryGateway.updatePost(uuid, title, description, commentaries);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void shouldCustomExceptionWhen_PostNotFound() {
        var uuid = MockBuilder.buildUUIDFromString();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        var commentaries = List.of(new Commentary());
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.empty());
        var customException = Assertions.catchThrowable(() -> {
            postRepositoryGateway.updatePost(uuid, title, description, commentaries);
        });
        Assertions.assertThat(customException).isNotNull();
    }
}
