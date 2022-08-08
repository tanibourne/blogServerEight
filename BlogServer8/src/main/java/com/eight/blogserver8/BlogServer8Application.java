package com.eight.blogserver8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogServer8Application {

    public static void main(String[] args) {
        SpringApplication.run(BlogServer8Application.class, args);
    }

}
