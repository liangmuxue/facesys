package com.ss.util.redis.cluster.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;


public class RedisClusterCommonCmd {

    private JedisCluster jedisCluster;

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    public RedisClusterCommonCmd(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    public RedisClusterCommonCmd() {
    }


    public String set(String key, String value) {
        return this.jedisCluster.set(key, value);
    }


    public String set(String key, String value, String nxxx, String expx, long time) {
        return this.jedisCluster.set(key, value, nxxx, expx, time);
    }


    public String get(String key) {
        return this.jedisCluster.get(key);
    }


    public Boolean exists(String key) {
        return this.jedisCluster.exists(key);
    }


    public Long setnx(String key, String value) {
        return this.jedisCluster.setnx(key, value);
    }


    public String setex(String key, int seconds, String value) {
        return this.jedisCluster.setex(key, seconds, value);
    }


    public Long expire(String key, int seconds) {
        return this.jedisCluster.expire(key, seconds);
    }


    public Long pexpire(String key, long milliseconds) {
        return this.jedisCluster.pexpire(key, milliseconds);
    }


    public Long ttl(String key) {
        return this.jedisCluster.ttl(key);
    }


    public Long incrBy(String key, long integer) {
        return this.jedisCluster.incrBy(key, integer);
    }


    public Long incr(String key) {
        return this.jedisCluster.incr(key);
    }


    public Double incrByFloat(String key, double value) {
        return this.jedisCluster.incrByFloat(key, value);
    }


    public Long decrBy(String key, long integer) {
        return this.jedisCluster.decrBy(key, integer);
    }


    public Long decr(String key) {
        return this.jedisCluster.decr(key);
    }


    public Long append(String key, String value) {
        return this.jedisCluster.append(key, value);
    }


    public String substr(String key, int start, int end) {
        return this.jedisCluster.substr(key, start, end);
    }


    public Long hset(String key, String field, String value) {
        return this.jedisCluster.hset(key, field, value);
    }


    public String hget(String key, String field) {
        return this.jedisCluster.hget(key, field);
    }


    public Long hsetnx(String key, String field, String value) {
        return this.jedisCluster.hsetnx(key, field, value);
    }


    public String hmset(String key, Map<String, String> hash) {
        return this.jedisCluster.hmset(key, hash);
    }


    public List<String> hmget(String key, String... fields) {
        return this.jedisCluster.hmget(key, fields);
    }


    public Long hincrBy(String key, String field, long value) {
        return this.jedisCluster.hincrBy(key, field, value);
    }


    public Boolean hexists(String key, String field) {
        return this.jedisCluster.hexists(key, field);
    }


    public Long hdel(String key, String... field) {
        return this.jedisCluster.hdel(key, field);
    }


    public Long hlen(String key) {
        return this.jedisCluster.hlen(key);
    }


    public Set<String> hkeys(String key) {
        return this.jedisCluster.hkeys(key);
    }


    public List<String> hvals(String key) {
        return this.jedisCluster.hvals(key);
    }


    public Map<String, String> hgetAll(String key) {
        return this.jedisCluster.hgetAll(key);
    }


    public Long rpush(String key, String... string) {
        return this.jedisCluster.rpush(key, string);
    }


    public Long lpush(String key, String... string) {
        return this.jedisCluster.lpush(key, string);
    }


    public Long llen(String key) {
        return this.jedisCluster.llen(key);
    }


    public List<String> lrange(String key, long start, long end) {
        return this.jedisCluster.lrange(key, start, end);
    }


    public String ltrim(String key, long start, long end) {
        return this.jedisCluster.ltrim(key, start, end);
    }


    public String lindex(String key, long index) {
        return this.jedisCluster.lindex(key, index);
    }


    public String lset(String key, long index, String value) {
        return this.jedisCluster.lset(key, index, value);
    }


    public String lpop(String key) {
        return this.jedisCluster.lpop(key);
    }


    public String rpop(String key) {
        return this.jedisCluster.rpop(key);
    }


    public Long sadd(String key, String... member) {
        return this.jedisCluster.sadd(key, member);
    }


    public Set<String> smembers(String key) {
        return this.jedisCluster.smembers(key);
    }


    public Long srem(String key, String... member) {
        return this.jedisCluster.srem(key, member);
    }


    public String spop(String key) {
        return this.jedisCluster.spop(key);
    }


    public Set<String> spop(String key, long count) {
        return this.jedisCluster.spop(key, count);
    }


    public Long scard(String key) {
        return this.jedisCluster.scard(key);
    }


    public Boolean sismember(String key, String member) {
        return this.jedisCluster.sismember(key, member);
    }


    public String srandmember(String key) {
        return this.jedisCluster.srandmember(key);
    }


    public List<String> srandmember(String key, int count) {
        return this.jedisCluster.srandmember(key, count);
    }


    public Long strlen(String key) {
        return this.jedisCluster.strlen(key);
    }


    public Long zadd(String key, double score, String member) {
        return this.jedisCluster.zadd(key, score, member);
    }


    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return this.jedisCluster.zadd(key, scoreMembers);
    }


    public Set<String> zrange(String key, long start, long end) {
        return this.jedisCluster.zrange(key, start, end);
    }


    public Long zrem(String key, String... member) {
        return this.jedisCluster.zrem(key, member);
    }


    public Double zincrby(String key, double score, String member) {
        return this.jedisCluster.zincrby(key, score, member);
    }


    public Long zrank(String key, String member) {
        return this.jedisCluster.zrank(key, member);
    }


    public Long zrevrank(String key, String member) {
        return this.jedisCluster.zrevrank(key, member);
    }


    public Set<String> zrevrange(String key, long start, long end) {
        return this.jedisCluster.zrevrange(key, start, end);
    }


    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return this.jedisCluster.zrangeWithScores(key, start, end);
    }


    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return this.jedisCluster.zrevrangeWithScores(key, start, end);
    }


    public Long zcard(String key) {
        return this.jedisCluster.zcard(key);
    }


    public Double zscore(String key, String member) {
        return this.jedisCluster.zscore(key, member);
    }


    public List<String> sort(String key) {
        return this.jedisCluster.sort(key);
    }


    public List<String> sort(String key, SortingParams sortingParameters) {
        return this.jedisCluster.sort(key, sortingParameters);
    }


    public Long zcount(String key, double min, double max) {
        return this.jedisCluster.zcount(key, min, max);
    }


    public Long zcount(String key, String min, String max) {
        return this.jedisCluster.zcount(key, min, max);
    }


    public Set<String> zrangeByScore(String key, double min, double max) {
        return this.jedisCluster.zrangeByScore(key, min, max);
    }


    public Set<String> zrangeByScore(String key, String min, String max) {
        return this.jedisCluster.zrangeByScore(key, min, max);
    }


    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
    }


    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
    }


    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
    }


    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }


    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
    }


    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }


    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return this.jedisCluster.zrevrangeByScore(key, max, min);
    }


    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return this.jedisCluster.zrevrangeByScore(key, max, min);
    }


    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }


    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }


    public Long zremrangeByRank(String key, long start, long end) {
        return this.jedisCluster.zremrangeByRank(key, start, end);
    }


    public Long zremrangeByScore(String key, double start, double end) {
        return this.jedisCluster.zremrangeByScore(key, start, end);
    }


    public Long zremrangeByScore(String key, String start, String end) {
        return this.jedisCluster.zremrangeByScore(key, start, end);
    }


    public Long zlexcount(String key, String min, String max) {
        return this.jedisCluster.zlexcount(key, min, max);
    }


    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return this.jedisCluster.linsert(key, where, pivot, value);
    }


    public Long lpushx(String key, String... string) {
        return this.jedisCluster.lpushx(key, string);
    }


    public Long rpushx(String key, String... string) {
        return this.jedisCluster.rpushx(key, string);
    }


    public List<String> blpop(int timeout, String key) {
        return this.jedisCluster.blpop(timeout, key);
    }


    public List<String> brpop(int timeout, String key) {
        return this.jedisCluster.brpop(timeout, key);
    }


    public Long del(String key) {
        return this.jedisCluster.del(key);
    }


    public String echo(String string) {
        return this.jedisCluster.echo(string);
    }

}
