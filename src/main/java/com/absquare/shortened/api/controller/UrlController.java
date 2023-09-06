package com.absquare.shortened.api.controller;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.api.dto.UrlShortenRequest;
import com.absquare.shortened.service.UrlService;
import com.absquare.shortened.service.UrlServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten")
    public ResponseEntity<ShortenedUrl> shortenUrl(@RequestBody @Valid UrlShortenRequest request) throws UnknownHostException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlService.shortenUrl(request.getUrl()));
    }
}
