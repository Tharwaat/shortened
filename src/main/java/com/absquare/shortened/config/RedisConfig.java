package com.absquare.shortened.config;

import com.absquare.shortened.api.dto.ShortenedUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ShortenedUrl> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ShortenedUrl> redisTemplate = new RedisTemplate<>();
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setConnectionFactory(connectionFactory);
        } catch (Exception exception) {
            log.error("Error getting Redis Template connection: {} ", exception.getLocalizedMessage());
        }
        return redisTemplate;
    }
}
