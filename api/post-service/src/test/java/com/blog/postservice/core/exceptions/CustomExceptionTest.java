package com.blog.postservice.core.exceptions;

import com.blog.postservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class CustomExceptionTest {

    @InjectMocks
    private CustomException customException;

    @Test
    void shouldCreateCustomExceptionWhen_SendObject() {
        var customException = new CustomException(
                HttpStatus.BAD_REQUEST,
                MockBuilder.createLocalDateTime(),
                "Text message from test custom exception."
        );
        Assertions.assertThat(customException).isNotNull();
        Assertions.assertThat(customException.getStatus()).isNotNull();
        Assertions.assertThat(customException.getDateTime()).isNotNull();
        Assertions.assertThat(customException.getMessage()).isNotNull();
    }
}
