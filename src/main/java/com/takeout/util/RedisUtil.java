package com.takeout.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;

    public void delKey(String key){
        redisTemplate.delete(key);
    }
    public void expire(String key, long time){
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public void valueSet(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }
    public Object valueGet(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void hashPut(String key, String hashkey, Object value){
        redisTemplate.opsForHash().put(key, hashkey, value);
    }
}
