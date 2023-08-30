package com.absquare.shortened.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.absquare.shortened.repository")
public class PresistenceConfig {
}
