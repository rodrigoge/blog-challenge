package com.blog.postservice.infrastructure.configurations;

import com.blog.postservice.core.exceptions.CustomException;
import com.blog.postservice.mocks.MockBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class GlobalHandlerExceptionTest {

    @InjectMocks
    private GlobalHandlerException globalHandlerException;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void shouldHandlerException() {
        var exception = new CustomException(
                HttpStatus.BAD_REQUEST,
                MockBuilder.createLocalDateTime(),
                "Error message test."
        );
        var response = globalHandlerException.handlerException(exception);
        Assertions.assertThat(response).isNotNull();
    }
}
