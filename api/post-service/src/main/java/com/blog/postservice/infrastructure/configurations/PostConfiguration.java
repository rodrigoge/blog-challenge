package com.blog.postservice.infrastructure.configurations;

import com.blog.postservice.adapters.persistence.PostRepository;
import com.blog.postservice.application.gateways.PostRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfiguration {

    @Bean
    PostRepositoryGateway postRepositoryGateway(PostRepository postRepository) {
        return new PostRepositoryGateway(postRepository);
    }
}
