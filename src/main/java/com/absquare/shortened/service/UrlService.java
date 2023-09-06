package com.absquare.shortened.service;

import com.absquare.shortened.api.dto.ShortenedUrl;

import java.net.UnknownHostException;

public interface UrlService {
    ShortenedUrl shortenUrl(String sourceUrl) throws UnknownHostException;
}
