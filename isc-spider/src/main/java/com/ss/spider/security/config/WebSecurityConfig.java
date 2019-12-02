package com.ss.spider.security.config;

import com.ss.spider.security.CustomCorsFilter;
import com.ss.spider.security.RestAuthenticationEntryPoint;
import com.ss.spider.security.auth.ajax.AjaxAuthenticationProvider;
import com.ss.spider.security.auth.ajax.AjaxLoginProcessingFilter;
import com.ss.spider.security.auth.jwt.JwtAuthenticationProvider;
import com.ss.spider.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.ss.spider.security.auth.jwt.SkipPathRequestMatcher;
import com.ss.spider.security.auth.jwt.extractor.TokenExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private TokenExtractor tokenExtractor;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtProperties jwtProperties;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT,
                this.successHandler, this.failureHandler, this.objectMapper);

        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter()
            throws Exception {
        List<String> pathsToSkip = new ArrayList<String>();
        pathsToSkip.add(TOKEN_REFRESH_ENTRY_POINT);
        pathsToSkip.add(FORM_BASED_LOGIN_ENTRY_POINT);
        String[] anonUrls = getAnonUrls();
        if (anonUrls.length > 0) {
            for (String anonUrl : anonUrls) {
                pathsToSkip.add(anonUrl);
            }
        }
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, "/**");
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
                this.failureHandler, this.tokenExtractor, matcher);

        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.ajaxAuthenticationProvider);
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }


    private String[] getAnonUrls() {
        String[] anonUrls = new String[0];
        if (this.jwtProperties != null && !StringUtils.isEmpty(this.jwtProperties.getAnonUrls())) {
            anonUrls = this.jwtProperties.getAnonUrls().split(",");
        }
        return anonUrls;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((HttpSecurity) ((HttpSecurity) ((HttpSecurity) http
                .csrf().disable())
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and())
                .authorizeRequests()

                .antMatchers(new String[]{FORM_BASED_LOGIN_ENTRY_POINT})).permitAll()

                .antMatchers(new String[]{TOKEN_REFRESH_ENTRY_POINT})).permitAll()

                .antMatchers(new String[]{"/console"})).permitAll()

                .antMatchers(getAnonUrls())).permitAll()
                .and())
                .authorizeRequests()

                .antMatchers(new String[]{"/**"})).authenticated()

                .and())
                .addFilterBefore(new CustomCorsFilter(),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessingFilter(),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
    }

}
