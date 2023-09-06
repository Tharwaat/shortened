package com.absquare.shortened.repository;

import com.absquare.shortened.model.url.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface UrlRepository extends JpaRepository<URL, Long> {


    default URL save(String sourceUrl, String hashValue, String hostName) {
        String shortURL = MessageFormat.format("{0}/{1}", hostName, hashValue);
        URL urlToSave = new URL(hashValue, shortURL, sourceUrl);
        return save(urlToSave);
    }
}
