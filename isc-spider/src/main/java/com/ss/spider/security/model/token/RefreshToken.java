package com.ss.spider.security.model.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.Optional;


public class RefreshToken implements JwtToken {

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }


    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);
        return Optional.of(new RefreshToken(claims));
    }


    public String getToken() {
        return null;
    }


    public Jws<Claims> getClaims() {
        return this.claims;
    }


    public String getJti() {
        return ((Claims) this.claims.getBody()).getId();
    }


    public String getSubject() {
        return ((Claims) this.claims.getBody()).getSubject();
    }

}
