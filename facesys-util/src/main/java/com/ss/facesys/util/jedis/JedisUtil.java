package com.ss.facesys.util.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


@Component
public final class JedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private Logger logger = LoggerFactory.getLogger(JedisUtil.class);


    public static final int EXPIRE_TIME = 86400;


    public static final int HALF_EXPIRE_TIME = 43200;


    public static final int TEN_MINUTES_TIME = 600;


    public boolean expire(String key, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS).longValue();
    }


    public boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key).booleanValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {

                this.redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    public Object get(String key) {
        return (key == null) ? null : this.redisTemplate.opsForValue().get(key);
    }


    public String getString(String key) {
        return (key == null) ? null : this.redisTemplate
                .opsForValue().get(key).toString();
    }


    public boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {

                set(key, value);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public long incr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return this.redisTemplate.opsForValue().increment(key, delta).longValue();
    }


    public long decr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return this.redisTemplate.opsForValue().increment(key, -delta).longValue();
    }


    public Object hget(String key, String item) {
        return this.redisTemplate.opsForHash().get(key, item);
    }


    public Map<Object, Object> hmget(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }


    public boolean hmset(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0L) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean hset(String key, String item, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean hset(String key, String item, Object value, long time) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0L) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }


    public boolean hHasKey(String key, String item) {
        return this.redisTemplate.opsForHash().hasKey(key, item).booleanValue();
    }


    public double hincr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, by).doubleValue();
    }


    public double hdecr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, -by).doubleValue();
    }


    public Set<Object> sGet(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return null;
        }
    }


    public boolean sHasKey(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value).booleanValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public long sSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values).longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (time > 0L) {
                expire(key, time);
            }
            return count.longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public long sGetSetSize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key).longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public long setRemove(String key, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().remove(key, values);
            return count.longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public List<Object> lGet(String key, long start, long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return null;
        }
    }


    public long lGetListSize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key).longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public Object lGetIndex(String key, long index) {
        try {
            return this.redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return null;
        }
    }


    public boolean lSet(String key, Object value) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean lSet(String key, Object value, long time) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (time > 0L) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean lSet(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean lSet(String key, List<Object> value, long time) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0L) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return false;
        }
    }


    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = this.redisTemplate.opsForList().remove(key, count, value);
            return remove.longValue();
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            return 0L;
        }
    }


    public String getUser(String userid) {
        String key = "USERINFO_" + userid;
        if (hasKey(key)) {
            return get(key).toString();
        }
        return null;
    }

}
