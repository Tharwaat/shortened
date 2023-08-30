package com.absquare.shortened.repository;

import com.absquare.shortened.model.url.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<URL, Long> {}
