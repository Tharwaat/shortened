package com.absquare.shortened.cache;

import com.absquare.shortened.api.dto.ShortenedUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisService implements CachingService{

    private final RedisTemplate<String, ShortenedUrl> redisTemplate;
 
    public void set(String cacheName, String key, ShortenedUrl value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(cacheName + key, value);
        redisTemplate.expire(cacheName + key, timeout, timeUnit);
    } 

    public ShortenedUrl get(String cacheName, String key) {
        return redisTemplate.opsForValue().get(cacheName + key);
    } 
 
    public Boolean hasKey(String cacheName, String key) { 
        return redisTemplate.hasKey(cacheName + key);
    } 
} 