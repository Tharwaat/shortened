package com.absquare.shortened.mapper;

import com.absquare.shortened.api.dto.ShortenedUrl;
import com.absquare.shortened.model.url.URL;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UrlMapper {
    @Mapping(target="key", source="url.hashValue")
    ShortenedUrl urlToShortenedUrl(URL url);
}
