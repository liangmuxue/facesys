package com.ss.spider.security.model.token;

import com.ss.spider.security.exception.ExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;


public class RawAccessJwtToken implements JwtToken {

    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }


    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | io.jsonwebtoken.MalformedJwtException | IllegalArgumentException | io.jsonwebtoken.SignatureException ex) {
            logger.error("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.error("JWT Token is expired", expiredEx);
            throw new ExpiredTokenException(this, "JWT Token expired", expiredEx);
        }
    }


    public String getToken() {
        return this.token;
    }

}
