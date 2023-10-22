package com.blog.postservice.infrastructure.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnumRequest {

    ASC("asc"), DESC("desc");

    private final String description;
}
