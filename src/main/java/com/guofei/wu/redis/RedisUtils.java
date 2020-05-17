package com.guofei.wu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/14
 */
public class RedisUtils {
    static String redisHost = "10.103.41.88";
    static int redisPort = 6378;

    public Jedis getJedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false);//jedis 第一次启动时，会报错
        jedisPoolConfig.setTestOnReturn(true);

        int timeout = 10;
        String redisPassword = null;

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, timeout, redisPassword);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //具体的命令
        } catch (Exception e) {
        } finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
//            if (jedis != null)
//                jedis.close();
        }
        return jedis;
    }


    public static Long strSet(String key, String value) {
        Jedis jedis = new Jedis(redisHost, redisPort);
        return jedis.lpush("mylist", "hh", "hehe", "heihei");
    }
}
