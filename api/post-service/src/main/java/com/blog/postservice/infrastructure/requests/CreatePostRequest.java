package com.blog.postservice.infrastructure.requests;

import com.blog.postservice.domain.entities.Commentary;

import java.util.List;

public record CreatePostRequest(String title, String description, List<Commentary> commentaries) {
}
