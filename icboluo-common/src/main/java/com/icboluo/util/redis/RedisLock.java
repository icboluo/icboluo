package com.icboluo.util.redis;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author icboluo
 */
@Component
public class RedisLock extends AbstractRedis {

    private static final String LOCK_PREFIX = null;

    private long lockExpire;


    /**
     * 常用的分布式锁加强版
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        return (Boolean) redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            long expireAt = System.currentTimeMillis() + lockExpire + 1;
            Boolean acquire = redisConnection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (Objects.equals(acquire, Boolean.TRUE)) {
                return true;
            } else {
                byte[] value = redisConnection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = redisConnection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + lockExpire + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }


    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
