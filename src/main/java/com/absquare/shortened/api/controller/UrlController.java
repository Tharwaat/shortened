package com.absquare.shortened.api.controller;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.api.dto.UrlShortenRequest;
import com.absquare.shortened.api.exception.ResourceNotFoundException;
import com.absquare.shortened.service.UrlService;
import com.absquare.shortened.service.UrlServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@Slf4j
@RestController
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/url/shorten")
    public ResponseEntity<ShortenedUrl> shortenURL(@RequestBody @Valid UrlShortenRequest request) throws UnknownHostException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlService.shortenUrl(request.getUrl()));
    }

    @GetMapping(value = "/{hashValue}")
    public ResponseEntity<Void> getSourceURL(@PathVariable("hashValue") String hashValue) throws ResourceNotFoundException {
        log.info("RECEIVED KEY: {}", hashValue);
        String sourceUrl = urlService.getSourceURL(hashValue).getSourceUrl();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Destination", sourceUrl)
                .build();
    }

    @DeleteMapping(value = "/{hashValue}")
    public ResponseEntity<Void> deleteShortURL(@PathVariable("hashValue") String hashValue) throws ResourceNotFoundException {
        log.info("RECEIVED KEY: {}", hashValue);
        urlService.deleteShortURL(hashValue);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
