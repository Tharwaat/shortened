package com.absquare.shortened;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortenedApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShortenedApplication.class, args);
	}
}
