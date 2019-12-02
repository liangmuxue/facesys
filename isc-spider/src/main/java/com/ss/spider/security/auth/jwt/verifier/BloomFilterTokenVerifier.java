package com.ss.spider.security.auth.jwt.verifier;

import org.springframework.stereotype.Component;


@Component
public class BloomFilterTokenVerifier implements TokenVerifier {

    public boolean verify(String jti) {
        return true;
    }

}
