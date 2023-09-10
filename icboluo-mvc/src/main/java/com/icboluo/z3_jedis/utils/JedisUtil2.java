package com.icboluo.z3_jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtil2 {
    private static int maxtotal;
    private static int maxidle;
    private static String host;
    private static int port;
    private static JedisPool jedisPool;

    static {
        //方式二：resourcebundle，专门读取properties
        ResourceBundle bondle = ResourceBundle.getBundle("jedis");
        maxtotal = Integer.parseInt(bondle.getString("maxtotal"));
        maxidle = Integer.parseInt(bondle.getString("maxidle"));
        host = bondle.getString("host");
        port = Integer.parseInt(bondle.getString("port"));
        //初始化连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxtotal);
        config.setMaxIdle(maxidle);
        jedisPool = new JedisPool(config, host, port);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}

