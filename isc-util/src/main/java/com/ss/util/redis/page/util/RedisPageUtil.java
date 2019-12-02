package com.ss.util.redis.page.util;

import com.ss.util.redis.cluster.util.RedisClusterCommonCmd;
import com.ss.util.redis.page.RedisPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;


public class RedisPageUtil {

    public static final int DEFAULT_EXPIRE = 180;
    public static final int DEFAULT_PAGE_EXPIRE = 180;

    public static void cacheQueryParam(Object cmd, String userId, String param, int expire) {
        if (cmd instanceof RedisClusterCommonCmd) {
            cmd = (RedisClusterCommonCmd) cmd;
            if (!((RedisClusterCommonCmd) cmd).exists(userId).booleanValue()) {
                ((RedisClusterCommonCmd) cmd).setnx(userId, param);
                ((RedisClusterCommonCmd) cmd).expire(userId, expire);
            } else {
                ((RedisClusterCommonCmd) cmd).setex(userId, expire, param);
            }
        } else {
            cmd = (RedisTemplate) cmd;
            if (!((RedisTemplate) cmd).hasKey(userId).booleanValue()) {
                ((RedisTemplate) cmd).opsForValue().set(userId, param, expire, TimeUnit.SECONDS);
            } else {
                ((RedisTemplate) cmd).opsForValue().set(userId, param, expire, TimeUnit.SECONDS);
            }
        }
    }


    public static String getCacheQueryParam(Object cmd, String userId) {
        if (cmd instanceof RedisClusterCommonCmd) {
            cmd = (RedisClusterCommonCmd) cmd;
            ((RedisClusterCommonCmd) cmd).expire(userId, 180);
            return ((RedisClusterCommonCmd) cmd).get(userId);
        }
        cmd = (RedisTemplate) cmd;
        ((RedisTemplate) cmd).expire(userId, 180L, TimeUnit.SECONDS);
        return (String) ((RedisTemplate) cmd).opsForValue().get(userId);
    }


    public static void cacheListData(Object cmd, String param, List<String> cacheData, int expire) {
        if (cmd instanceof RedisClusterCommonCmd) {
            cmd = (RedisClusterCommonCmd) cmd;
            if (((RedisClusterCommonCmd) cmd).exists(param).booleanValue()) {
                ((RedisClusterCommonCmd) cmd).del(param);
            }
            String[] arrayData = new String[cacheData.size()];
            cacheData.toArray(arrayData);
            ((RedisClusterCommonCmd) cmd).rpush(param, arrayData);
            ((RedisClusterCommonCmd) cmd).expire(param, expire);
        } else {
            cmd = (RedisTemplate) cmd;
            if (((RedisTemplate) cmd).hasKey(param).booleanValue()) {
                ((RedisTemplate) cmd).delete(param);
            }
            ((RedisTemplate) cmd).opsForList().rightPushAll(param, cacheData);
            ((RedisTemplate) cmd).expire(param, expire, TimeUnit.SECONDS);
        }
    }


    public static RedisPage<String> getCachePageData(Object cmd, String param, int currentPage, int pageSize) {
        RedisPage<String> page = new RedisPage<String>(currentPage, pageSize, true, Boolean.valueOf(false));

        if (cmd instanceof RedisClusterCommonCmd) {
            cmd = (RedisClusterCommonCmd) cmd;
            page.setTotal(((RedisClusterCommonCmd) cmd)
                    .llen(param).longValue());

            page.addAll(((RedisClusterCommonCmd) cmd)
                    .lrange(param, page.getStartRow(), page.getEndRow()));

            ((RedisClusterCommonCmd) cmd).expire(param, 180);
        } else {
            cmd = (RedisTemplate) cmd;
            page.setTotal(((RedisTemplate) cmd)
                    .opsForList().size(param).longValue());

            page.addAll(((RedisTemplate) cmd)
                    .opsForList().range(param, page.getStartRow(), page.getEndRow()));

            ((RedisTemplate) cmd).expire(param, 180L, TimeUnit.SECONDS);
        }

        return page;
    }

}
