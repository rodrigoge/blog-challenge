package com.blog.commentaryservice.infrastructure.configurations;

import com.blog.commentaryservice.core.exceptions.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException> handlerException(CustomException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new CustomException(
                        exception.getStatus(),
                        exception.getDateTime(),
                        exception.getMessage()
                ));
    }
}
