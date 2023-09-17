package com.absquare.shortened.service;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.api.exception.ResourceNotFoundException;
import com.absquare.shortened.cache.RedisService;
import com.absquare.shortened.mapper.UrlMapper;
import com.absquare.shortened.model.url.URL;
import com.absquare.shortened.repository.UrlRepository;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final HashService hashService;
    private final UrlMapper urlMapper;
    private final RedisService cachingService;
    private final HttpServlet httpServlet;
    private final String SHORT_URL_CACHE_NAME = "short_urls";

    @Value("${host.name:127.0.0.1}")
    private String hostName;


    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, MD5HashServiceImpl hashService,
                          UrlMapper urlMapper, RedisService cachingService, HttpServlet httpServlet) {
        this.urlRepository = urlRepository;
        this.hashService = hashService;
        this.urlMapper = urlMapper;
        this.cachingService = cachingService;
        this.httpServlet = httpServlet;
    }

    @Override
    public ShortenedUrl shortenUrl(String sourceUrl) {
        String hashValue = hashService.createHashForUrl(sourceUrl);
        if (cachingService.hasKey(SHORT_URL_CACHE_NAME, hashValue)) {
            return cachingService.get(SHORT_URL_CACHE_NAME, hashValue);
        }
        URL savedURL = urlRepository.save(sourceUrl, hashValue, hostName);
        ShortenedUrl result = urlMapper.urlToShortenedUrl(savedURL);
        cachingService.set(SHORT_URL_CACHE_NAME, hashValue, result, 5, TimeUnit.MINUTES);
        return result;
    }

    @Override
    public ShortenedUrl getSourceURL(String hashValue) throws ResourceNotFoundException {
        if (cachingService.hasKey(SHORT_URL_CACHE_NAME, hashValue)) {
            return cachingService.get(SHORT_URL_CACHE_NAME, hashValue);
        }

        ShortenedUrl foundURL = urlRepository.findByHashValue(hashValue)
                .map(urlMapper::urlToShortenedUrl)
                .orElseThrow(() -> new ResourceNotFoundException(hashValue));

        cachingService.set(SHORT_URL_CACHE_NAME, hashValue, foundURL, 5, TimeUnit.MINUTES);
        return foundURL;
    }
}
