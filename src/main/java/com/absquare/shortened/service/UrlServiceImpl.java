package com.absquare.shortened.service;

import com.absquare.shortened.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void shortenUrl(String sourceUrl) {
        urlRepository.findById(1);
    }
}
