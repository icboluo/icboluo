package com.icboluo.z3_jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author icboluo
 */
public class JedisUtil {
    private static int maxtotal;
    private static int maxidle;
    private static String host;
    private static int port;
    private static JedisPool jedisPool;

    //加载数据
    static {
        //读取web项目下的src资源：
        //1.类加载器
        InputStream inputStream = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            maxtotal = Integer.parseInt(p.getProperty("maxtotal"));
            maxidle = Integer.parseInt(p.getProperty("maxidle"));
            host = p.getProperty("host");
            port = Integer.parseInt(p.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //方式二：resourcebundle，专门读取properties
        ResourceBundle bundle = ResourceBundle.getBundle("jedis");
        maxtotal = Integer.parseInt(bundle.getString("maxtotal"));
        maxidle = Integer.parseInt(bundle.getString("maxidle"));
        host = bundle.getString("host");
        port = Integer.parseInt(bundle.getString("port"));
        // 创建配置对象, 初始化连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxtotal);
        config.setMaxIdle(maxidle);
        // 创建连接池对象
        jedisPool = new JedisPool(config, host, port);
    }

    //获取连接
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
