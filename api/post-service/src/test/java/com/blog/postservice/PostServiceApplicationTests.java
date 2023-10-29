package com.blog.postservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostServiceApplicationTests {

    @LocalServerPort
    private int randomPort;

    @Test
    void contextLoads() {
    }
}
