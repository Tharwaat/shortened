package com.absquare.shortened.api.controller;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.api.dto.UrlShortenRequest;
import com.absquare.shortened.service.UrlService;
import com.absquare.shortened.service.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    ResponseEntity<ShortenedUrl> shortenUrl(@RequestBody UrlShortenRequest request) {
        urlService.shortenUrl("String");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ShortenedUrl.builder().sourceUrl(request.getUrl()).build());
    }
}
