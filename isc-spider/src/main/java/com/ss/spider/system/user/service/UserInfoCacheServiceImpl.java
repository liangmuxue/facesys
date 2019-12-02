package com.ss.spider.system.user.service;

import com.ss.spider.interceptor.cache.beans.CacheUserInfo;
import com.ss.spider.interceptor.service.UserInfoCacheService;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.spider.system.role.model.Role;
import com.ss.spider.system.role.service.RoleService;
import com.ss.spider.system.user.model.User;
import com.ss.spider.system.user.model.UserRole;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Service("userInfoCacheService")
public class UserInfoCacheServiceImpl implements UserInfoCacheService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserRoleService<UserRole> userRoleService;
    @Autowired
    private UserService<User> userService;
    @Autowired
    private ResourceService<Resource> resourceService;
    @Autowired
    private OrganizationService<Organization> organizationService;
    @Autowired
    private RoleService<Role> roleService;

    @Override
    public String getCacheToken(String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }


    @Override
    public void addCache(String key, String value, int expTimeMin) {
        this.redisTemplate.opsForValue().set(key, value, expTimeMin, TimeUnit.MINUTES);
    }

    @Override
    public List<String> getCacheRoles(String key) {
        return (List) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setCacheRoles(String key, List<String> roles, int expTimeMin) {
        this.redisTemplate.opsForValue().set(key, roles, expTimeMin, TimeUnit.MINUTES);
    }

    @Override
    public List<String> getPersistenceRoles(final String key) {
        List<String> rets = null;
        final List<User> list = this.userService.gets(new HashMap<String, Object>(1) {

        });
        if (null == list || list.isEmpty()) {
            return null;
        }

        List<UserRole> userRoles = this.userRoleService.list(new UserRole() {

        });
        if (null != userRoles && !userRoles.isEmpty()) {
            final List<String> ids = (List) userRoles.stream().map(UserRole::getRoleId)
                    .collect(Collectors.toList());
            Map<String, Object> params = new HashMap<String, Object>(2) {

            };
            List<Role> roles = this.roleService.gets(params);
            if (!CollectionUtils.isEmpty(roles)) {
                rets = (List) roles.stream().map(Role::getRoleId).collect(Collectors.toList());
            }
        }

        return rets;
    }

    @Override
    public List<String> getCacheResources(String key) {
        return (List) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setCacheResources(String key, List<String> resources, int expTimeMin) {
        this.redisTemplate.opsForValue().set(key, resources, expTimeMin, TimeUnit.MINUTES);
    }

    @Override
    public List<String> getPersistenceResources(final String key) {
        List<String> rets = null;
        final List<User> list = this.userService.gets(new HashMap<String, Object>(1) {

        });
        if (null == list || list.isEmpty()) {
            return null;
        }

        List<Resource> resources = this.resourceService.query(new HashMap<String, Object>() {

        });
        if (null != resources && !resources.isEmpty()) {
            rets = new ArrayList<String>(resources.size());
            for (Resource res : resources) {
                if (!StringUtils.isEmpty(res.getUrl())) {
                    rets.add(res.getUrl().trim());
                }
            }
        }

        return rets;
    }

    @Override
    public String getCacheUserId(String userName) {
        return (String) this.redisTemplate.opsForValue().get(userName);
    }

    @Override
    public CacheUserInfo getCacheUserInfo(String key) {
        String json = (String) this.redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return (CacheUserInfo) JSON.parseObject(json, CacheUserInfo.class);
    }

    @Override
    public void setCacheUserInfo(String key, CacheUserInfo cacheUserInfo, int expTimeMin) {
        this.redisTemplate.opsForValue()
                .set(key, JSON.toJSONString(cacheUserInfo), expTimeMin, TimeUnit.MINUTES);
    }

    @Override
    public CacheUserInfo getPersistenceUserInfo(String userId) {
        User user = (User) this.userService.get(userId);
        if (null == user) {
            return null;
        }

        CacheUserInfo cacheUserInfo = new CacheUserInfo();
        BeanUtils.copyProperties(user, cacheUserInfo);

        if (null != cacheUserInfo && !StringUtils.isEmpty(cacheUserInfo.getOrgId())) {
            Organization org = (Organization) this.organizationService.get(cacheUserInfo.getOrgId());
            if (null != org) {
                cacheUserInfo.setOrgName(org.getOrgCname());
            }
        }

        return cacheUserInfo;
    }

}
