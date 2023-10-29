package com.blog.commentaryservice.infrastructure.configurations;

import com.blog.commentaryservice.adapters.persistence.CommentaryRepository;
import com.blog.commentaryservice.adapters.persistence.PostRepository;
import com.blog.commentaryservice.application.gateways.CommentaryRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentaryConfiguration {

    @Bean
    CommentaryRepositoryGateway commentaryRepositoryGateway(CommentaryRepository commentaryRepository,
                                                            PostRepository postRepository) {
        return new CommentaryRepositoryGateway(commentaryRepository, postRepository);
    }
}
