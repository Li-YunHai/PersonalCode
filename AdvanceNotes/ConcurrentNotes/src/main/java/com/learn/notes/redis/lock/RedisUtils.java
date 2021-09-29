package com.learn.notes.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(20);
        jpc.setMaxIdle(10);
        jedisPool = new JedisPool(jpc);
    }

    public static Jedis getJedis() throws Exception{
        if(jedisPool == null)
            throw new NullPointerException("JedisPool is not OK.");
        return jedisPool.getResource();
    }

}

