package com.icboluo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 */
@Component
public class StringRedis<T> extends AbstractRedis {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    @Resource
    private ValueOperations<String, T> valueOperations;

    @SuppressWarnings("unused")
    private void example() {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Optional<T> get(String key) {
        return key == null ? Optional.empty() : Optional.ofNullable(valueOperations.get(key));
    }

    public T getOfNullAble(String key) {
        return this.get(key).orElse(null);
    }

    /**
     * 添加缓存
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, T value) {
        try {
            valueOperations.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, T value, long time) {
        return set(key, value, time, TimeUnit.SECONDS);
    }

    public boolean set(String key, T value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                valueOperations.set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增操作
     *
     * @param key   键
     * @param delta 递增因子
     * @return 递增之后的值
     */
    public Long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return valueOperations.increment(key, delta);
    }

    /**
     * 递减操作
     *
     * @param key 键
     * @return 递减之后的值
     */
    public Long decrease(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return valueOperations.increment(key, -delta);
    }
}
