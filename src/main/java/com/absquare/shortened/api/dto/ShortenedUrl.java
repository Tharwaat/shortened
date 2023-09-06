package com.absquare.shortened.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortenedUrl {
    private String key;
    private String sourceUrl;
    private String shortUrl;
}
