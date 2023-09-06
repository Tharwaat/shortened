package com.absquare.shortened.model.url;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = URL.TABLE_NAME)
public class URL {
    public static final String TABLE_NAME = "url";
    public static final String SOURCE_URL_COLUMN_NAME = "source_url";
    public static final String SHORT_URL_COLUMN_NAME = "short_url";
    public static final String HASH_VALUE_COLUMN_NAME = "hash_value";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = URL.SOURCE_URL_COLUMN_NAME)
    private String sourceUrl;

    @Column(name = URL.SHORT_URL_COLUMN_NAME)
    private String shortUrl;

    @Column(name = URL.HASH_VALUE_COLUMN_NAME)
    private String hashValue;

    public URL(String hashValue, String shortUrl, String sourceUrl) {
        this.hashValue = hashValue;
        this.shortUrl = shortUrl;
        this.sourceUrl = sourceUrl;
    }
}


