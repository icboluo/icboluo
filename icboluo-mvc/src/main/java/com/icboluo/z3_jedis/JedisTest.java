package com.icboluo.z3_jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "8999");
        jedis.set("first", "998");
        String name = jedis.get("name");
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        //创建配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数 total全部的，完全的
        config.setMaxTotal(100);
        //最大空闲连接数
        config.setMaxIdle(20);

        //创建jedispool
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        //从连接池中获取数据
        Jedis jedis = jedisPool.getResource();
        //操作数据
        jedis.set("name", "西瓜");
        String s = jedis.get("name");
        //释放资源
        jedis.close();
    }

    @Test
    public void testJedisPool1() {
        Jedis jedis = JedisUtil.getJedis();
        String name = jedis.get("name");
        System.out.println(name);
    }
}
