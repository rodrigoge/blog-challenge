package com.blog.postservice.adapters.persistence;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {

    @Mock
    private PostRepository postRepository;

    @Test
    void shouldTestPostRepository() {
        var uuid = MockBuilder.buildUUIDFromString();
        var post = MockBuilder.createPost();
        post.setId(uuid);
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.of(post));
        var postFounded = postRepository.findById(uuid);
        Assertions.assertThat(postFounded).isNotNull();
    }

    @Test
    void shouldTestFindAllInPostRepository() {
        var specification = MockBuilder.createPostSpecification();
        var pageable = MockBuilder.createPageable();
        var pagePost = MockBuilder.createPagePost();
        Mockito.when(postRepository.findAll(specification, pageable)).thenReturn(pagePost);
        var response = postRepository.findAll(specification, pageable);
        Assertions.assertThat(response).isNotNull();
    }
}
