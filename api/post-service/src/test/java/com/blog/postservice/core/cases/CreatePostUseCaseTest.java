package com.blog.postservice.core.cases;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePostUseCaseTest {

    @Mock
    private CreatePostUseCase createPostUseCase;

    @Test
    void shouldCreatePost() {
        var postResponse = MockBuilder.createPostResponse();
        var title = "Title mock test";
        var description = "Description mocked from the test in application.";
        Mockito.when(createPostUseCase.createPost(title, description)).thenReturn(postResponse);
        var response = createPostUseCase.createPost(title, description);
        Assertions.assertThat(response).isNotNull();
    }
}
