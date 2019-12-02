package com.ss.spider.system.user.service;

import com.ss.enums.StatusEnum;
import com.ss.spider.security.UserTokenService;
import com.ss.spider.security.entity.UserAuthEntity;
import com.ss.spider.system.user.mapper.UserMapper;
import com.ss.spider.system.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("userTokenService")
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    private User getUser(final String loginName) {
        List<User> list = this.userMapper.gets(new HashMap<String, Object>(1) {{
            put("loginName",loginName);
        }});
        return !CollectionUtils.isEmpty(list) ? (User) list.get(0) : null;
    }


    @Override
    public UserAuthEntity getUserByLoginName(String loginName) {
        User user = getUser(loginName);
        if (user != null) {
            UserAuthEntity userAuthEntity = new UserAuthEntity();
            userAuthEntity.setUsername(user.getLoginName());
            userAuthEntity.setPassword(user.getPassword());
            userAuthEntity.setUserId(user.getUserId());
            userAuthEntity.setStatus(user.getStatus());
            return userAuthEntity;
        }

        return null;
    }

    /**
     * 验证登陆密码
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public boolean isAuthSuccess(String loginName, String password) {
        User user = getUser(loginName);
        if (user == null || StatusEnum.EFFECT.getCode() != user.getStatus().intValue()) {
            return false;
        }

//        if (StringUtils.hasText(user.getSalt())) {
//            password = password + user.getSalt();
//        }

//        if (!this.bcrypt.matches(password, user.getPassword())) {
//            return false;
//        }

        if (!password.equals(user.getPassword())) {
            return false;
        }

        return true;
    }


    @Override
    public void cacheUserToken(String key, String token, Integer timeOut) {
        this.redisTemplate.opsForValue().set(key, token, timeOut.intValue(), TimeUnit.MINUTES);
    }

    @Override
    public void cacheUserId(String userName, String userId, Integer timeOut) {
        this.redisTemplate.opsForValue().set(userName, userId, timeOut.intValue(), TimeUnit.MINUTES);
    }

    @Override
    public void cleanCacheByKey(String key) {
        this.redisTemplate.delete(key);
    }

    @Override
    public String getUserToken(String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }

}
