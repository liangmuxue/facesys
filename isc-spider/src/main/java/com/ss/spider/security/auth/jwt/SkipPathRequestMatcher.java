package com.ss.spider.security.auth.jwt;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;


public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        Assert.notNull(pathsToSkip);

        List<RequestMatcher> m = new ArrayList<RequestMatcher>();
        for (String path : pathsToSkip) {
            m.add(new AntPathRequestMatcher(path));
        }

        this.matchers = new OrRequestMatcher(m);
        this.processingMatcher = new AntPathRequestMatcher(processingPath);
    }


    public boolean matches(HttpServletRequest request) {
        if (this.matchers.matches(request)) {
            return false;
        }

        return this.processingMatcher.matches(request);
    }

}
