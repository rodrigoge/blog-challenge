package com.blog.postservice.adapters.mappers;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostMapperTest {

    private PostMapper postMapper;

    @BeforeEach
    public void setup() {
        postMapper = Mappers.getMapper(PostMapper.class);
    }

    @Test
    void shouldMappingPostResponseWhen_SendPostEntity() {
        var post = MockBuilder.createPost();
        var response = postMapper.fromPostEntity(post);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.title()).isEqualTo(post.getTitle());
        Assertions.assertThat(response.description()).isEqualTo(post.getDescription());
    }
}
