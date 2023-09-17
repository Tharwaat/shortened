package com.absquare.shortened.service;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.api.exception.ResourceNotFoundException;

import java.net.UnknownHostException;

public interface UrlService {
    ShortenedUrl shortenUrl(String sourceUrl) throws UnknownHostException;
    ShortenedUrl getSourceURL(String hashValue) throws ResourceNotFoundException;
}
