package com.ss.spider.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;


public final class AccessJwtToken implements JwtToken {

    private final String rawToken;
    @JsonIgnore
    private Claims claims;

    protected AccessJwtToken(String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }


    public String getToken() {
        return this.rawToken;
    }


    public Claims getClaims() {
        return this.claims;
    }

}
