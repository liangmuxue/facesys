package com.ss.spider.security.endpoint;

import com.ss.controller.AbstractController;
import com.ss.enums.CommonEnumClass;
import com.ss.response.ResponseEntity;
import com.ss.spider.security.UserTokenService;
import com.ss.spider.security.auth.jwt.extractor.TokenExtractor;
import com.ss.spider.security.config.JwtProperties;
import com.ss.spider.security.entity.UserAuthEntity;
import com.ss.spider.security.model.UserContext;
import com.ss.spider.security.model.token.AccessJwtToken;
import com.ss.spider.security.model.token.JwtToken;
import com.ss.spider.security.model.token.JwtTokenFactory;
import com.ss.spider.security.model.token.RawAccessJwtToken;
import com.ss.spider.security.model.token.RefreshToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RefreshTokenEndpoint extends AbstractController {

    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtProperties jwtSettings;
    @Autowired
    private UserTokenService useTokenrService;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @RequestMapping({"/auth/token"})
    public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = request.getHeader("X-Authorization");
        String tokenPayload = this.tokenExtractor.extract(token);


        if (!validToken(tokenPayload, this.jwtSettings.getTokenSigningKey())) {
            return new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getCode(), CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getDesc());
        }


        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        Optional<RefreshToken> refreshToken = RefreshToken.create(rawToken, this.jwtSettings.getTokenSigningKey());
        String subject = ((RefreshToken) refreshToken.get()).getSubject();
        UserAuthEntity user = this.useTokenrService.getUserByLoginName(subject);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + subject);
        }


        String cacheToken = this.useTokenrService.getUserToken("TOKEN_" + subject);
        if (!StringUtils.isEmpty(cacheToken) &&
                validToken(cacheToken, this.jwtSettings.getTokenSigningKey())) {
            ResponseEntity<Map<String, String>> responseEntity = createSuccResponse();
            Map<String, String> result = new HashMap<String, String>(3);
            result.put("token", cacheToken);
            result.put("refreshToken", this.useTokenrService.getUserToken("REFRESH_TOKEN_" + subject));
            result.put("userId", user.getUserId());
            responseEntity.setData(result);
            return responseEntity;
        }


        UserContext userContext = UserContext.create(user.getUsername(), new ArrayList());
        AccessJwtToken accessJwtToken = this.tokenFactory.createAccessJwtToken(userContext);
        JwtToken refJwtToken = this.tokenFactory.createRefreshToken(userContext);

        ResponseEntity<Map<String, String>> responseEntity = createSuccResponse();
        Map<String, String> result = new HashMap<String, String>(2);
        result.put("token", accessJwtToken.getToken());
        result.put("refreshToken", refJwtToken.getToken());
        result.put("userId", user.getUserId());
        responseEntity.setData(result);


        this.useTokenrService.cacheUserToken("TOKEN_" + subject, accessJwtToken.getToken(), this.tokenFactory.getJwtProperties().getTokenExpirationTime());
        this.useTokenrService.cacheUserToken("REFRESH_TOKEN_" + subject, refJwtToken.getToken(), this.tokenFactory.getJwtProperties().getRefreshTokenExpTime());

        this.useTokenrService.cacheUserId("USERNAME_" + subject, user.getUserId(), this.tokenFactory.getJwtProperties().getTokenExpirationTime());

        return responseEntity;
    }


    private boolean validToken(String token, String signKey) {
        try {
            RawAccessJwtToken rawToken = new RawAccessJwtToken(token);
            Optional<RefreshToken> refreshToken = RefreshToken.create(rawToken, signKey);
            if (!refreshToken.isPresent()) {
                return false;
            }
        } catch (Exception expe) {
            return false;
        }
        return true;
    }

}
