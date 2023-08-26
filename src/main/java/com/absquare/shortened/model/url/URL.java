package com.absquare.shortened.model.url;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class URL {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "key")
    private String key;
}
