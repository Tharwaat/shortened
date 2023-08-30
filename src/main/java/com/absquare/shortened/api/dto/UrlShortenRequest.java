package com.absquare.shortened.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortenRequest {
    @JsonProperty(value = "url", required = true)
    @NotNull(message = "URL is missing")
    @NotBlank(message = "URL must have a value")
    @URL(message = "Invalid URL Format")
    String url;
}
