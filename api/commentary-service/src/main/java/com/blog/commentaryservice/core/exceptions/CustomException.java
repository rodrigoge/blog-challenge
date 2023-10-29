package com.blog.commentaryservice.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus status;
    private final LocalDateTime dateTime;
    private final String message;

    public CustomException(HttpStatus status, LocalDateTime dateTime, String message) {
        super(message);
        this.status = status;
        this.dateTime = dateTime;
        this.message = message;
    }
}
