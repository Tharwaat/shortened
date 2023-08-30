package com.absquare.shortened.service;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.mapper.UrlMapper;
import com.absquare.shortened.model.url.URL;
import com.absquare.shortened.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;
    private final HashService hashService;
    private final UrlMapper urlMapper;

    @Value("${host.name:127.0.0.1}")
    private String hostName;


    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, MD5HashServiceImpl hashService, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.hashService = hashService;
        this.urlMapper = urlMapper;
    }

    @Override
    public ShortenedUrl shortenUrl(String sourceUrl) {
        String hashValue = hashService.createHashForUrl(sourceUrl);
        String shortURL = MessageFormat.format("{0}/{1}", hostName, hashValue);

        URL urlToSave = new URL(hashValue, shortURL, sourceUrl);
        URL savedURL = urlRepository.save(urlToSave);

        return urlMapper.urlToShortenedUrl(savedURL);
    }
}
