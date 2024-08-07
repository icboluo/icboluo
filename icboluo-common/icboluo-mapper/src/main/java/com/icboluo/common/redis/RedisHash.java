package com.icboluo.common.redis;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>哈希类型相关操作
 * <p>使用场景：适合用于存储对象的各个属性，如用户信息，商品信息等
 * <p>注意：redis 类型在某个地方要单独使用，不要混用；redisList 和 redisHash如果混用，会难以维护
 *
 * @author icboluo
 */
@Component
public class RedisHash<T> extends AbstractRedis<T> {

    @Resource
    private HashOperations<String, String, T> hashOperations;

    @SuppressWarnings("all")
    private void example() {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
    }


    //1、设置一组Map的键值对

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public T hget(String key, String item) {
        return hashOperations.get(key, item);
    }

    /**
     * 获取hashKey对应的所有值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<String, T> hmget(String key) {
        return hashOperations.entries(key);
    }

    public <O> O hmget(String key, Class<O> clazz) {
        Map<String, T> entries = hashOperations.entries(key);
        return JSON.parseObject(JSON.toJSONString(entries), clazz);
    }
//        3、添加一个Map类型值

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, T> map) {
        try {
            hashOperations.putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//        4、添加一个Map类型值并设置过期时间

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public void hmset(String key, Map<String, T> map, long time) {
        hashOperations.putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    public void hmset(String key, Object obj, long time) {
        hashOperations.putAll(key, (Map<? extends String, ? extends T>) JSON.parseObject(JSON.toJSONString(obj)));
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hset(String key, String item, T value) {
        hashOperations.put(key, item, value);
    }

//        6、向一张hash表中放入数据,如果不存在将创建并设置过期时间

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public void hset(String key, String item, T value, long time) {
        hashOperations.put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, T... item) {
        hashOperations.delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return hashOperations.hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return hashOperations.increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return hashOperations.increment(key, item, -by);
    }
}
