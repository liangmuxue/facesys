package com.ss.spider.security.auth.jwt;

import com.ss.spider.security.auth.JwtAuthenticationToken;
import com.ss.spider.security.config.JwtProperties;
import com.ss.spider.security.model.UserContext;
import com.ss.spider.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtAuthenticationProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(this.jwtProperties.getTokenSigningKey());
        UserContext context = UserContext.create(((Claims) jwsClaims.getBody()).getSubject(), new ArrayList());

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }


    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
