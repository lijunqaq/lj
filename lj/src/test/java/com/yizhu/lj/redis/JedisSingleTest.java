package com.yizhu.lj.redis;

import org.apache.velocity.runtime.directive.contrib.For;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/10/18
 */
public class JedisSingleTest {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "lijunqaq.top", 6379, 3000);

        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
            System.out.println(jedis.set("single","lijun"));
            System.out.println(jedis.get("single"));
            Pipeline pl = jedis.pipelined();
            for(int i=0;i<10;i++){
                pl.incr("pipelineKey");
                pl.set("lj"+i,"lj");

            }
            List<Object> objects = pl.syncAndReturnAll();
            System.out.println(objects);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
            if(jedis !=null);
            jedis.close();
        }
    }
}
