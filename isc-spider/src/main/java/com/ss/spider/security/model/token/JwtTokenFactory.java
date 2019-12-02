package com.ss.spider.security.model.token;

import com.ss.spider.security.config.JwtProperties;
import com.ss.spider.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class JwtTokenFactory {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenFactory(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public JwtProperties getJwtProperties() {
        return this.jwtProperties;
    }


    public AccessJwtToken createAccessJwtToken(UserContext ctxt) {
        if (!StringUtils.hasText(ctxt.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }


        Claims claims = Jwts.claims().setSubject(ctxt.getUsername());
        LocalDateTime currentTime = LocalDateTime.now();


        String token = Jwts.builder().setClaims(claims).setIssuer(this.jwtProperties.getTokenIssuer()).setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant())).setExpiration(Date.from(currentTime.plusMinutes(this.jwtProperties.getTokenExpirationTime().intValue()).atZone(ZoneId.systemDefault()).toInstant())).signWith(SignatureAlgorithm.HS512, this.jwtProperties.getTokenSigningKey()).compact();

        return new AccessJwtToken(token, claims);
    }


    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isEmpty(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(userContext.getUsername());


        String token = Jwts.builder().setClaims(claims).setIssuer(this.jwtProperties.getTokenIssuer()).setId(UUID.randomUUID().toString()).setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant())).setExpiration(Date.from(currentTime.plusMinutes(this.jwtProperties.getRefreshTokenExpTime().intValue()).atZone(ZoneId.systemDefault()).toInstant())).signWith(SignatureAlgorithm.HS512, this.jwtProperties.getTokenSigningKey()).compact();

        return new AccessJwtToken(token, claims);
    }

}
