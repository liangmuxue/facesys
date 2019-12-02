package com.ss.spider.security.auth.jwt;

import com.ss.spider.security.auth.JwtAuthenticationToken;
import com.ss.spider.security.auth.jwt.extractor.TokenExtractor;
import com.ss.spider.security.exception.ExpiredTokenException;
import com.ss.spider.security.exception.RefreshTokenException;
import com.ss.spider.security.model.token.RawAccessJwtToken;
import com.ss.spider.security.wrapper.CustomHeaderWrapper;
import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;


public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String token = request.getHeader("X-Authorization");

        try {
            /**
             * <p>By:Francis</p>
             * <p>查询header中的token，并put入map中</p>
             * <p>若header中token为空，则在parameter中查询token，查到则更新到map中</p>
             */
            CustomHeaderWrapper customHeaderWrapper;
            Map<String, String> map = new HashMap<String, String>();
            map.put("X-Authorization", token);

            if (StringUtils.isEmpty(token)) {
                String newToken = request.getParameter("X-Authorization");
                if (!StringUtils.isEmpty(newToken))
                    map.put("X-Authorization", newToken);
            }
            customHeaderWrapper = new CustomHeaderWrapper(request, map);
            super.doFilter(customHeaderWrapper, res, chain);
        } catch (ExpiredTokenException e) {
            unsuccessfulAuthentication(request, response, new RefreshTokenException(e.getMessage()));
        } catch (ExpiredJwtException e) {
            unsuccessfulAuthentication(request, response, new RefreshTokenException(e.getMessage()));
        }
    }


    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("X-Authorization");
        RawAccessJwtToken token = new RawAccessJwtToken(this.tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
