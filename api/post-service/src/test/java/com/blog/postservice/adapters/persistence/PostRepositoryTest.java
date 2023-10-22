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
        var uuid = UUID.fromString("64aad137-45eb-46f5-ac45-0919563886b2");
        var post = MockBuilder.createPost();
        post.setId(uuid);
        Mockito.when(postRepository.findById(uuid)).thenReturn(Optional.of(post));
        var postFounded = postRepository.findById(uuid);
        Assertions.assertThat(postFounded).isNotNull();
    }
}
