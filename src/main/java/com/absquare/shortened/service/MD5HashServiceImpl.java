package com.absquare.shortened.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class MD5HashServiceImpl implements HashService {
    @Override
    public String createHashForUrl(String url) {
        return DigestUtils.md5Hex(url).substring(0, 7);
    }
}
