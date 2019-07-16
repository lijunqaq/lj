package com.example.lj.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2018/11/19
 */

@Component
public class RedisUtil {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);

    }

    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 获取redis中所有数据
     */
    public HashMap<String,String> getAll(){
        //获得所有的key
        Set<String> keys = redisTemplate.keys("*");
        HashMap<String,String> map = new HashMap<>();
        for (String key:keys){
            String value = redisTemplate.opsForValue().get(key);
            map.put(key,value);
        }
        return map;
    }


    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param key
     * @param value
     * @param time  单位：秒
     * @return
     */
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
